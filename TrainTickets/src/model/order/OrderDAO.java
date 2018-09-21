package model.order;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface OrderDAO {
	//向数据库中添加一个订单
	public Order addOrder(Order order);
	
	//修改订单付款信息
	public void updateOrderStatus(String oid, String status);
	
	//删除订单
	public void deleteOrder(String oid);
	
	//根据用户ID获取到订单信息
	public ArrayList<Order> getOrderByUid(String uid);
	
	//锁定订单座位
	public HashMap<String,String> lockSeat(String tid, String date, String ctype, String nextTo, int fromIndex, int toIndex);
}
