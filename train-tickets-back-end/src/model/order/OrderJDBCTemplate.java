package model.order;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//订单相关方法接口实现
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
		String SQL = "insert into `12307`.order (user_id, idcard, user_name, train_id, train_name, carriage, "
				+ "seat_type, seat_id, seat_location, start_time, start_stop_id, start_station_name, end_stop_id, "
				+ "end_station_name, date, create_at, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplateObject.update(SQL, order.getUserId(), order.getPassengerId(), order.getUserName(),
				order.getTrainId(), order.getTrainName(), order.getCarriage(), order.getSeatType(), order.getSeatId(),
				order.getSeatLocation(), order.getStartTime(), order.getStartStopId(), order.getStartStationName(),
				order.getEndStopId(), order.getEndStationName(), order.getDate(), order.getCreateAt(),
				order.getStatus());

		SQL = "select * from `12307`.order as o where idcard = ? and seat_type = ? and seat_id = ?";
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		ArrayList<Order> orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, order.getPassengerId(),
				order.getSeatType(), order.getSeatId());

		System.out.println("Create order complete!");
		return orderList.get(0);
	}

	@Override
	public void updateOrder(String orderId, Order order) {
		String SQL = "update `12307`.order set user_id = ?, idcard = ?, user_name = ?, train_id = ?, train_name = ?, carriage = ?, "
				+ "seat_type = ?, seat_id = ?, seat_location = ?, start_time = ?, start_stop_id = ?, start_station_name = ?, end_stop_id = ?, "
				+ "end_station_name = ?, date = ?, create_at = ?, status = ? where order_id = ?";
		jdbcTemplateObject.update(SQL, order.getUserId(), order.getPassengerId(), order.getUserName(),
				order.getTrainId(), order.getTrainName(), order.getCarriage(), order.getSeatType(), order.getSeatId(),
				order.getSeatLocation(), order.getStartTime(), order.getStartStopId(), order.getStartStationName(),
				order.getEndStopId(), order.getEndStationName(), order.getDate(), order.getCreateAt(),
				order.getStatus(), orderId);
		System.out.println("Update order complete!");
	}

	@Override
	public void deleteOrderByOrderId(String orderId) {
		String SQL = "delete from `12307`.order where order_id = ?";
		jdbcTemplateObject.update(SQL, orderId);
		System.out.println("Delete order complete!");
	}

	@Override
	public ArrayList<Order> getOrderByIdcard(String idcard) {
		String SQL = "select * from `12307`.order where idcard = ?";
		RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
		ArrayList<Order> orderList = (ArrayList<Order>) jdbcTemplateObject.query(SQL, rowMapper, idcard);
		return orderList;
	}
}
