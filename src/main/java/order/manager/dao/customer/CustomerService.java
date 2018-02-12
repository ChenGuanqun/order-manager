package order.manager.dao.customer;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
public interface CustomerService {

    boolean insert(Customer customer);

    boolean deleteById(long id);

    boolean updateById(Customer customer, String operator);

    List<Customer> queryList(CustomerQuery query);

    int queryCount(CustomerQuery query);
}
