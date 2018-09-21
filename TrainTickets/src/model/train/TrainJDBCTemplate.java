package model.train;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Scope("prototype")
public class TrainJDBCTemplate implements TrainDAO {
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
	public void addTrain(Train train) {
		String SQL = "insert into `train` (`tname`, `type`) values (?, ?)";
		jdbcTemplateObject.update(SQL, train.getTname(), train.getType());

		SQL = "select * from `train` where tname = ?";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> output = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper, train.getTname());
		
		if (output.size() == 1) {
			train.setTid(output.get(0).getTid());

			ArrayList<TrainStop> stopInfo = train.getStopInfo();
			for (int a = 0; a < stopInfo.size(); a++) {
				SQL = "insert into `stop` (`tid`, `sid`, `city`, `station`, `arrive_time`, `stop_time`, `mileage`) values (?, ?, ?, ?, ?, ?, ?)";
				jdbcTemplateObject.update(SQL, train.getTid(), a, stopInfo.get(a).getCity(),
						stopInfo.get(a).getStation(), stopInfo.get(a).getArriveTime(), stopInfo.get(a).getStopTime(),
						stopInfo.get(a).getMileage());
			}

			System.out.println("Create train complete!");
		} else {
			System.out.println("查询结果出错！查询出了" + output.size() + "个结果。");
		}
	}

	@Override
	public void addTrainCarriage(String type, ArrayList<TrainCarriage> carriage) {
		for (int a = 0; a < carriage.size(); a++) {
			String SQL = "insert into `carriage` (`type`, `cid`, `ctype`, `capacity`) values (?, ?, ?, ?)";
			jdbcTemplateObject.update(SQL, type, a, carriage.get(a).getCtype(), carriage.get(a).getCapacity());
		}
		System.out.println("Create train type complete!");
	}

	@Override
	public void updateTrain(String tid, Train train) {
		String SQL = "update `train` set `tname` = ?, `type` = ?, `start` = ?, `end` = ?, `start_time` = ?, `end_time` = ? where `tid` = ?";
		jdbcTemplateObject.update(SQL, train.getTname(), train.getType(), train.getStart(), train.getEnd(),
				train.getStartTime(), train.getEndTime(), tid);
		System.out.println("Update train complete!");
	}

	@Override
	public void updateTrainCarriage(String type, String cid, TrainCarriage carriage) {
		String SQL = "update `carriage` set `ctype` = ?, `capacity` = ? where `type` = ? and `cid` = ?";
		jdbcTemplateObject.update(SQL, carriage.getCtype(), carriage.getCapacity(), type, cid);
		System.out.println("Update train carriage complete!");
	}

	@Override
	public void updateTrainStop(String tid, String sid, TrainStop stop) {
		String SQL = "update `stop` set `city` = ?, `station` = ?, `arrive_time` = ?, `stop_time` = ?, `mileage` = ? where `tid` = ? and `sid` = ?";
		jdbcTemplateObject.update(SQL, stop.getCity(), stop.getStation(), stop.getArriveTime(), stop.getStopTime(),
				stop.getMileage(), tid, sid);
		System.out.println("Update train stop complete!");
	}

	@Override
	public void deleteTrain(String tid) {
		String SQL = "delete from `train` where tid = ?";
		jdbcTemplateObject.update(SQL, tid);

		SQL = "delete from `stop` where tid = ?";
		jdbcTemplateObject.update(SQL, tid);

		System.out.println("Delete train complete!");
	}

	@Override
	public ArrayList<Train> getTrainByStop(String startCity, String endCity, String date) {
		String SQL = "select a.tid, tname, a.sid as start_index, b.sid as end_index, a.station as start, b.station as end, a.arrive_time as start_time, b.arrive_time as end_time,b.mileage-a.mileage as mileage "
				+ "from `stop` as a,`stop` as b,`train` as t "
				+ "where a.city = ? and b.city = ? and a.sid < b.sid and a.tid = b.tid and a.tid = t.tid and a.tid not in ( " + "select tid "
				+ "from `status` " + "where date = ? and status = '停运') ";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> trainList = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper, startCity, endCity, date);
		return trainList;
	}
	
	@Override
	public ArrayList<TransferTrain> getTrainTransfer(String startCity, String endCity, String date) {
		String SQL = "select a.tid as firstTid, d.tid as secondTid, t1.tname as firstTname, t2.tname as secondTname, b.station as transfer_station " + 
				"from `12307`.stop as a, `12307`.stop as b, `12307`.stop as c, `12307`.stop as d, `12307`.train as t1, `12307`.train as t2 " + 
				"where a.city = ? and d.city = ? and a.tid = b.tid and b.station = c.station and c.tid = d.tid and b.tid <> c.tid and a.sid < b.sid and c.sid < d.sid and b.arrive_time < c.arrive_time " + 
				"and a.tid not in (select tid from `status` where date = ? and status = '停运') " + 
				"and d.tid not in (select tid from `status` where date = ? and status = '停运') " +
				"and a.tid = t1.tid and d.tid = t2.tid ";
		RowMapper<TransferTrain> rowMapper = new BeanPropertyRowMapper<>(TransferTrain.class);
		ArrayList<TransferTrain> transferTrainList = (ArrayList<TransferTrain>) jdbcTemplateObject.query(SQL, rowMapper, startCity, endCity, date, date);
		return transferTrainList;
	}

	@Override
	public Train getTrainByTid(String tid) {
		String SQL = "select * from `train` where tid = ?";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> trainList = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper, tid);

		if (trainList.size() == 1) {
			SQL = "select * from `stop` where tid = ?";
			RowMapper<TrainStop> stopMapper = new BeanPropertyRowMapper<>(TrainStop.class);
			ArrayList<TrainStop> trainStopList = (ArrayList<TrainStop>) jdbcTemplateObject.query(SQL, stopMapper, tid);

			Train output = trainList.get(0);
			output.setStopInfo(trainStopList);
			return output;
		} else {
			System.out.println("查询火车出现错误！");
			System.out.println("共查询出" + trainList.size() + "条数据。");
			return null;
		}
	}

	@Override
	public ArrayList<TrainSeats> getRemainingSeats(String tid, String date, String fromSid, String toSid) {
		String SQL = "select c.ctype, sum(capacity) as seats_count " + 
				"from `12307`.train as t,`12307`.carriage as c " + 
				"where t.tid = ? and t.type = c.type and capacity > 0 " + 
				"group by c.ctype";
		RowMapper<TrainSeats> rowMapper = new BeanPropertyRowMapper<>(TrainSeats.class);
		ArrayList<TrainSeats> allSeats = (ArrayList<TrainSeats>) jdbcTemplateObject.query(SQL, rowMapper, tid);
		
		SQL = "select t.tid, c.ctype, count(o.oid) as seats_count " + 
				"from `12307`.train as t,`12307`.carriage as c, `12307`.order as o " + 
				"where t.tid = ? and o.date = ? and t.type = c.type and o.tid = t.tid and o.cid = c.cid and capacity > 0 and ((o.start_sid >= ? and o.start_sid <= ?) or (o.end_sid >= ? and o.end_sid <= ?)) " + 
				"group by c.ctype";
		rowMapper = new BeanPropertyRowMapper<>(TrainSeats.class);
		ArrayList<TrainSeats> soldSeats = (ArrayList<TrainSeats>) jdbcTemplateObject.query(SQL, rowMapper, tid, date, fromSid, toSid, fromSid, toSid);
		
		for(int a = 0; a < allSeats.size(); a++) {
			for(int b = 0; b < soldSeats.size(); b++) {
				if(allSeats.get(a).getCtype().equals(soldSeats.get(b).getCtype())) {
					allSeats.get(a).setSeatsCount(allSeats.get(a).getSeatsCount() - soldSeats.get(b).getSeatsCount());
				}
				
			}
		}
		return allSeats;
	}

	@Override
	public ArrayList<String> getAllCities() {
		String SQL = "select distinct city from `stop`";
		RowMapper<TrainStop> rowMapper = new BeanPropertyRowMapper<>(TrainStop.class);
		ArrayList<TrainStop> cityStopList = (ArrayList<TrainStop>) jdbcTemplateObject.query(SQL, rowMapper);
		ArrayList<String> cityList = new ArrayList<String>();
		
		for(int a = 0; a < cityStopList.size(); a++) {
			cityList.add(cityStopList.get(a).getCity());
		}
		return cityList;
	}
	
	@Override
	public ArrayList<Train> getAllTrains() {
		String SQL = "select * from `train`";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> trainList = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper);
		return trainList;
	}
}
