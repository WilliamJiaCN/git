package com.hivescm.tms.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.hivescm.cache.client.JedisClient;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.constants.ExceptionCodeConstants;

@Service
public class RedisUserInfoUtils {
	
//	@Autowired
//	protected JedisClient<User> jedis; 
//	
//	/**
//	 * 组织权限
//	 */
//	public ArrayList getBranch(String opUserId) {
//		opUserId="2160187";
//		User user=jedis.getPojo(opUserId);
//		if(user==null){
//			 throw new SystemException(ExceptionCodeConstants.ERROR_CREATE_REGULATION_INFO, "用户信息过时，请重新登录");
//		}
//		ArrayList dataPermissions=(ArrayList) user.getMenus().get("orgs");
//		ArrayList permissions=new ArrayList();
//		for (int i = 0; i < dataPermissions.size(); i++) {
//			HashMap dataPermission=(HashMap)dataPermissions.get(i);
//			permissions.add(dataPermission);
//		}
//		processDataOfOrg(permissions);
//		return permissions;
//	}
//	
//	private void processDataOfOrg(ArrayList<HashMap<String,Object>> permissions) {
//		for(HashMap<String,Object> permission:permissions){
//			ArrayList<String> keys=new ArrayList<String>();
//			for(String key:permission.keySet()){
//				if(!(key.equals("permissionId") || key.equals("orgCode") || key.equals("orgName") || key.equals("childOrgs"))){
//					keys.add(key);
//				}
//			}
//			for(String key:keys){
//				permission.remove(key);
//			}
//			if(permission.get("childOrgs")!=null && !permission.get("childOrgs").toString().equals("[]")){
//				processDataOfOrg((ArrayList<HashMap<String,Object>>)permission.get("childOrgs"));
//			}
//		}
//		
//	}
//
//	/**
//	 * 区域权限
//	 */
//	public ArrayList getArea(@RequestParam String opUserId) {
//		opUserId="2160187";
//		User user=jedis.getPojo(opUserId);
//		if(user==null){
//			 throw new SystemException(ExceptionCodeConstants.ERROR_CREATE_REGULATION_INFO, "用户信息过时，请重新登录");
//		}
//		ArrayList dataPermissions=(ArrayList) user.getMenus().get("datas");
//		ArrayList permissions=new ArrayList();
//		for (int i = 0; i < dataPermissions.size(); i++) {
//			HashMap dataPermission=(HashMap)dataPermissions.get(i);
//			if(dataPermission.get("managementId").toString().equals("4")){//这是区域权限
//				permissions.add(dataPermission);
//			}
//		}
//		processDataOfArea(permissions);
//		return permissions;
//	}
//
//	private void processDataOfArea(ArrayList<HashMap<String,Object>> permissions) {
//		for(HashMap<String,Object> permission:permissions){
//			ArrayList<String> keys=new ArrayList<String>();
//			for(String key:permission.keySet()){
//				if(!(key.equals("dataId") || key.equals("dataResource") || key.equals("childDatas"))){
//					keys.add(key);
//				}
//			}
//			for(String key:keys){
//				permission.remove(key);
//			}
//			if(permission.get("childDatas")!=null && !permission.get("childDatas").toString().equals("[]")){
//				processDataOfArea((ArrayList<HashMap<String,Object>>)permission.get("childDatas"));
//			}
//		}
//		
//	}
//
//	/**
//	 * 客户权限
//	 */
//	public ArrayList<String> getClient(@RequestParam String opUserId) {
//		opUserId="2160187";
//		User user=jedis.getPojo(opUserId);
//		if(user==null){
//			 throw new SystemException(ExceptionCodeConstants.ERROR_CREATE_REGULATION_INFO, "用户信息过时，请重新登录");
//		}
//		ArrayList dataPermissions=(ArrayList) user.getMenus().get("datas");
//		ArrayList permissions=new ArrayList();
//		for (int i = 0; i < dataPermissions.size(); i++) {
//			HashMap dataPermission=(HashMap)dataPermissions.get(i);
//			if(dataPermission.get("managementId").toString().equals("5")){//这是客户权限
//				permissions.add(dataPermission);
//			}
//		}
//		ArrayList<String> ret=new ArrayList<String>();
//		processDataOfClient(permissions,ret);
//		return ret;
//	}
//
//	private void processDataOfClient(ArrayList<HashMap> permissions,ArrayList<String> ret) {
//		for(HashMap permission:permissions){
//			ret.add(permission.get("dataId").toString());
//			if(permission.get("childDatas")!=null && !permission.get("childDatas").toString().equals("[]")){
//				processDataOfClient((ArrayList<HashMap>)permission.get("childDatas"),ret);
//			}
//		}
//	}

}
