package com.dzz.ioc.transaction;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public interface PlatformTransactionManager {

    /**
     * spring 事务的思考
     *  如何处理及实现嵌套事务
     * mysql本身不支持事务的嵌套
     *
     * 目前任务：实现required的传播级别
     * 当前没有事务则创建事务，有则加入事务
     *
     *
     */
}
