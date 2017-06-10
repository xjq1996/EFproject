package com.ef.video.service;

import java.io.Serializable;

import com.ef.video.dao.CommonDao;

public abstract class CommonService<T,ID extends Serializable> {
	protected CommonDao<T,ID> commonDao;

	public void setCommonDao(CommonDao<T, ID> commonDao) {
		this.commonDao = commonDao;
	}

	public CommonDao<T, ID> getCommonDao() {
		return commonDao;
	}
	
	/**
	 * 根据ID获取某个Entity
	 * @param id
	 * @return
	 */
	public T get(ID id) {
		return commonDao.getOne(id);
	}
}
