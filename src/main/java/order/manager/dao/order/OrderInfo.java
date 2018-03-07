package order.manager.dao.order;

import lombok.Data;
import order.manager.dao.abst.BaseBean;

/**
 * @author chentao01
 * @version 2018/2/12
 */
@Data
public class OrderInfo extends BaseBean {
    private Long id;
    private String customerName;
    private Long productId;
    private String productName;
    private String productSeries;
    private Long number;
    private Long deliveryDate;
    private Long orderDate;
    private Long planDate;
    private Integer status;
    private Integer deliveryStatus;
    private String config;
    private Integer orderId;
    private Integer orderYear;

    /**
     * 油漆
     */
    private Integer yq;

    /**
     * 定子
     */
    private Integer dz;

    /**
     * 转子
     */
    private Integer zz;

    /**
     * 风罩
     */
    private Integer fz;

    /**
     * 钢板件
     */
    private Integer hbj;

    /**
     * 轴
     */
    private Integer zc;
    /**
     * 盖头
     */
    private Integer gttt;

    /**
     * 机壳
     */
    private Integer jk;

    /**
     * 转子总成
     */
    private Integer zzzc;

    /**
     * 定子总成
     */
    private Integer dzzc;

    /**
     * 包装
     */
    private Integer bz;

    /**
     * 标牌
     */
    private Integer bp;

    /**
     * 开关
     */
    private Integer sc;

    /**
     * 线缆
     */
    private Integer lc;

    public void resetInitConfig() {
/**
 * 油漆
 */
        yq = 1;

        /**
         * 定子
         */
        dz = 1;

        /**
         * 转子
         */
        zz = 1;

        /**
         * 风罩
         */
        fz = 1;

        /**
         * 钢板件
         */
        hbj = 1;

        /**
         * 轴
         */
        zc = 1;
        /**
         * 盖头
         */
        gttt = 1;

        /**
         * 机壳
         */
        jk = 1;

        /**
         * 转子总成
         */
        zzzc = 1;

        /**
         * 定子总成
         */
        dzzc = 1;

        /**
         * 包装
         */
        bz = 1;

        /**
         * 标牌
         */
        bp = 1;

        /**
         * 开关
         */
        sc = 1;

        /**
         * 线缆
         */
        lc = 1;
    }
}
