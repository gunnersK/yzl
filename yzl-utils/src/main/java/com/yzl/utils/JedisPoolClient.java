package com.yzl.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolClient {

	private static JedisPool pool = null;
	/**
	 * 
	 * �������� ����redis���ӳ�
	 *
	 * @return
	 * 
	 * @author yaomy
	 * @date 2018��1��11�� ����4:53:07
	 */
	static {
		if(pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			//����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
            //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
			config.setMaxTotal(50);
			//����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
			config.setMaxIdle(5);
			//��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException����λ����
			//С����:������ȷ����ʱ��,  Ĭ��-1
			config.setMaxWaitMillis(1000*100);
			//��borrow(����)һ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
			config.setTestOnBorrow(true);
			//return һ��jedisʵ����poolʱ���Ƿ������ӿ����ԣ�ping()��
			config.setTestOnReturn(true);
			//connectionTimeout ���ӳ�ʱ��Ĭ��2000ms��
			//soTimeout ��Ӧ��ʱ��Ĭ��2000ms��
			pool = new JedisPool(config, "47.98.175.75", 6379,2000);//2000, "619868"
		}
	}
	/**
	 * 
	 * �������� ��ȡJedisʵ��
	 *
	 * @return
	 * 
	 * @author yaomy
	 * @date 2018��1��11�� ����4:56:58
	 */
	public static Jedis getJedis() {
		return pool.getResource();
	}
	/**
	 * 
	 * �������� �ͷ�jedis������Դ
	 *
	 * @param jedis
	 * 
	 * @author yaomy
	 * @date 2018��1��12�� ����10:36:07
	 */
	public static void returnResource(Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}
	}
}
