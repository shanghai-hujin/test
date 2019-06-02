package com.example.hasee.newwork.cache;

/**
 * cache  操作接口定义
 */
public interface ICache {

    /**
     * store key , value
     * @param key  must be String
     * @param value  Object
     */
    void put(String key, Object value);

    /**
     * Get the value according to the key
     * @param key   String
     * @return  Object
     */
    Object get(String key);

    /**
     * delete data by key
     * @param key String
     */
    void remove(String key);

    /**
     * Contains the value
     * @param key  String
     * @return   Whether or not
    Contains the value
     */
    boolean contains(String key);

    /**
     * Clean all
     */
    void clear();

}
