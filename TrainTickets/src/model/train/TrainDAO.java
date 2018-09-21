package model.train;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface TrainDAO {
	//向数据库中添加一列火车
	public void addTrain(Train train);
	
	//向数据库中添加一种车型
	public void addTrainCarriage(String type, ArrayList<TrainCarriage> carriage);
	
	//修改火车信息
	public void updateTrain(String tid, Train train);
	
	//修改火车车厢信息
	public void updateTrainCarriage(String type, String cid, TrainCarriage carriage);
		
	//修改火车经停信息
	public void updateTrainStop(String tid, String sid, TrainStop stop);
	
	//删除火车信息
	public void deleteTrain(String tid);
	
	//根据车站获取火车ID
	public ArrayList<Train> getTrainByStop(String startCity, String endCity, String date);
	
	//火车换乘
	public ArrayList<TransferTrain> getTrainTransfer(String startCity, String endCity, String date);
	
	//根据火车ID获取火车信息
	public Train getTrainByTid(String tid);
	
	//查询火车座位剩余
	public ArrayList<TrainSeats> getRemainingSeats(String tid, String date, String fromSid, String toSid);
	
	//查询所有通火车的城市
	public ArrayList<String> getAllCities();

	//获取所有火车信息
	public ArrayList<Train> getAllTrains();
}
