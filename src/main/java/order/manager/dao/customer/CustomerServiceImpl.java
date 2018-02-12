package order.manager.dao.customer;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chentao01
 * @version 2018/2/11
 */
@Component
public class CustomerServiceImpl implements CustomerService{
    @Override
    public boolean insert(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public boolean updateById(Customer customer, String operator) {
        return false;
    }

    @Override
    public List<Customer> queryList(CustomerQuery query) {
        return null;
    }

    @Override
    public int queryCount(CustomerQuery query) {
        return 0;
    }
}
