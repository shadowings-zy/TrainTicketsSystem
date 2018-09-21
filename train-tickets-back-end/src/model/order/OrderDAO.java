package model.order;

import java.util.ArrayList;

// 订单相关方法接口
public interface OrderDAO {
	//向数据库中添加一个订单
	public Order addOrder(Order order);
	
	//修改订单信息
	public void updateOrder(String orderId, Order order);
	
	//删除订单
	public void deleteOrderByOrderId(String orderId);
	
	//根据用户ID获取到订单信息
	public ArrayList<Order> getOrderByIdcard(String idcard);
}
