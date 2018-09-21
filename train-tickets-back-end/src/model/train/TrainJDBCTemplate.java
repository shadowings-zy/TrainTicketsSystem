package model.train;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// 火车相关方法实现
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
	public ArrayList<String> getAllCities() {
		String SQL = "select distinct city_name from `stop`";
		RowMapper<TrainStop> rowMapper = new BeanPropertyRowMapper<>(TrainStop.class);
		ArrayList<TrainStop> cityStopList = (ArrayList<TrainStop>) jdbcTemplateObject.query(SQL, rowMapper);
		ArrayList<String> cityList = new ArrayList<String>();
		
		for(int a = 0; a < cityStopList.size(); a++) {
			cityList.add(cityStopList.get(a).getCityName());
		}
		return cityList;
	}
	
	@Override
	public ArrayList<Train> getTrainByStop(String startCity, String endCity, String date) {
		String SQL = "select a.train_id, train_name, a.stop_id as start_index, b.stop_id as end_index, a.station_name as start, b.station_name as end, a.arrive_time as start_time, b.arrive_time as end_time, (b.mileage - a.mileage) as mileage, t.train_type " + 
				"from `12307`.stop as a, `12307`.stop as b, `12307`.train as t " + 
				"where a.city_name = ? and b.city_name = ? and a.stop_id < b.stop_id and a.train_id = b.train_id and a.train_id = t.train_id and a.train_id not in " + 
				"(select train_id from `12307`.status where date = ? and status = '停运')";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> trainList = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper, startCity, endCity, date);
		return trainList;
	}
	
	@Override
	public ArrayList<TransferTrain> getTrainTransfer(String startCity, String endCity, String date) {
		String SQL = "select a.train_id as first_tid, d.train_id as second_tid, e.train_name as first_tname, f.train_name as second_tname, b.station_name as transfer_station " + 
				"from `12307`.stop as a, `12307`.stop as b, `12307`.stop as c, `12307`.stop as d, `12307`.train as e, `12307`.train as f " + 
				"where a.city_name = ? and d.city_name = ? and a.train_id = b.train_id and b.station_name = c.station_name and c.train_id = d.train_id and b.train_id <> c.train_id and a.stop_id < b.stop_id and c.stop_id < d.stop_id and b.arrive_time < c.arrive_time " + 
				"and a.train_id not in (select train_id from `12307`.status where date = ? and status = '停运') " + 
				"and d.train_id not in (select train_id from `12307`.status where date = ? and status = '停运') " + 
				"and a.train_id = e.train_id and d.train_id = f.train_id";
		RowMapper<TransferTrain> rowMapper = new BeanPropertyRowMapper<>(TransferTrain.class);
		ArrayList<TransferTrain> transferTrainList = (ArrayList<TransferTrain>) jdbcTemplateObject.query(SQL, rowMapper, startCity, endCity, date, date);
		return transferTrainList;
	}

	@Override
	public Train getTrainByTrainId(String trainId) {
		String SQL = "select * from `12307`.train where train_id = ?";
		RowMapper<Train> rowMapper = new BeanPropertyRowMapper<>(Train.class);
		ArrayList<Train> trainList = (ArrayList<Train>) jdbcTemplateObject.query(SQL, rowMapper, trainId);

		SQL = "select * from `12307`.stop where train_id = ?";
		RowMapper<TrainStop> stopMapper = new BeanPropertyRowMapper<>(TrainStop.class);
		ArrayList<TrainStop> trainStopList = (ArrayList<TrainStop>) jdbcTemplateObject.query(SQL, stopMapper, trainId);
		
		SQL = "select * from `12307`.seat where train_id = ?";
		RowMapper<TrainSeats> seatsMapper = new BeanPropertyRowMapper<>(TrainSeats.class);
		ArrayList<TrainSeats> trainSeatsList = (ArrayList<TrainSeats>) jdbcTemplateObject.query(SQL, seatsMapper, trainId);

		Train output = trainList.get(0);
		output.setStopInfo(trainStopList);
		output.setSeatsInfo(trainSeatsList);
		return output;
	}

	@Override
	public ArrayList<TrainSeats> getRemainingSeats(String trainId, String date, String fromStopId, String toStopId) {
		String SQL = "select s.seat_id, s.carriage, s.seat_type, s.seat_location " + 
				"from `12307`.train as t, `12307`.seat as s " + 
				"where t.train_id = ? and t.train_id = s.train_id and s.seat_id not in ( " + 
				"select seat_id from `12307`.order where train_id = ? and date = ? and ((start_stop_id >= ? and start_stop_id < ?) or (end_stop_id > ? and end_stop_id <= ?)))";
		RowMapper<TrainSeats> rowMapper = new BeanPropertyRowMapper<>(TrainSeats.class);
		ArrayList<TrainSeats> allSeats = (ArrayList<TrainSeats>) jdbcTemplateObject.query(SQL, rowMapper, trainId, trainId, date, fromStopId, toStopId, fromStopId, toStopId);
		return allSeats;
	}
}
