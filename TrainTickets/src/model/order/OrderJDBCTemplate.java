package model.order;

import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Scope("prototype")
public class OrderJDBCTemplate implements OrderDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Order addOrder(Order order) {
		String SQL = "insert into `order` (`uid`, `passenger_id`, `passenger_name`, `tid`, `cid`, `location`, `start_sid`, `end_sid`, `date`, `create_at`, `status`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, order.getUid(), order.getPassengerId(), order.getPassengerName(), order.getTid(),
				order.getCid(), order.getLocation(), order.getStartSid(), order.getEndSid(), order.getDate(),
				order.getCreatAt(), order.getStatus());
		
		SQL = "select * from `order` as o where tid = ? and cid = ? and location = ? and date = ? and start_sid = ? and end_sid = ? ";
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		ArrayList<Order> orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, order.getTid(), order.getCid(), order.getLocation(), order.getDate(), order.getStartSid(), order.getEndSid());
		
		System.out.println("Create order complete!");
		return orderList.get(0);
	}

	@Override
	public void updateOrderStatus(String oid, String status) {
		String SQL = "update `order` set `status` = ? where `oid` = ?";
		jdbcTemplateObject.update(SQL, status, oid);
		System.out.println("Update order complete!");
	}

	@Override
	public void deleteOrder(String oid) {
		String SQL = "delete from `order` where oid = ?";
		jdbcTemplateObject.update(SQL, oid);
		System.out.println("Delete order complete!");
	}

	@Override
	public ArrayList<Order> getOrderByUid(String uid) {
		String SQL = "select o.oid, uid, passenger_id, passenger_name, o.tid, o.cid, location, o.start_sid, o.end_sid, date, create_at, status, tname, s1.city as from_city, s2.city as to_city "
				+ "from `order` as o,`train` as t, `stop` as s1 , `stop` as s2 "
				+ "where uid = ? and t.tid = o.tid and t.tid = s1.tid and o.start_sid = s1.sid and t.tid = s2.tid and o.end_sid = s2.sid ";
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		ArrayList<Order> orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, uid);
		return orderList;
	}

	@Override
	public HashMap<String, String> lockSeat(String tid, String date, String ctype, String nextTo, int fromIndex, int toIndex) {
		String SQL = "select c1.cid, s.location " + "from `12307`.seat as s, `12307`.carriage as c1 "
				+ "where c1.ctype = ? and s.next_to = ? and (c1.cid, s.location) not in ( " + "select c2.cid,o.location "
				+ "from `12307`.order as o, `12307`.carriage as c2 "
				+ "where o.tid = ? and c2.ctype = ? and o.date = ? and c2.cid = o.cid and ((o.start_sid <= ? and o.end_sid <= ?) or (o.start_sid >= ? and o.end_sid >= ?))) ";

		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		ArrayList<Order> orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, ctype, nextTo, tid, ctype,
				date, fromIndex, toIndex, fromIndex, toIndex);
		
		if(orderList.isEmpty()) {
			SQL = "select c1.cid, s.location " + "from `12307`.seat as s, `12307`.carriage as c1 "
					+ "where c1.ctype = ? and (c1.cid, s.location) not in ( " + "select c2.cid,o.location "
					+ "from `12307`.order as o, `12307`.carriage as c2 "
					+ "where o.tid = ? and c2.ctype = ? and o.date = ? and c2.cid = o.cid and ((o.start_sid <= ? and o.end_sid <= ?) or (o.start_sid >= ? and o.end_sid >= ?))) ";

			rowMapper = new BeanPropertyRowMapper<>(Order.class);
			orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, ctype, tid, ctype,
					date, fromIndex, toIndex, fromIndex, toIndex);
		}
		
		HashMap<String, String> output = new HashMap<String, String>();
		output.put("cid", orderList.get(0).getCid());
		output.put("location", orderList.get(0).getLocation());
		return output;
	}

}
