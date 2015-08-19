package com.smis.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import com.smis.dao.ApplicationMapper;
import com.smis.dao.TeacherApplicationMapper;
import com.smis.dao.TeacherMapper;
import com.smis.exception.BusinessJsonException;
import com.smis.exception.ParameterJsonException;
import com.smis.model.Application;
import com.smis.model.Teacher;
import com.smis.model.TeacherApplication;
import com.smis.model.TeacherApplicationKey;
import com.smis.service.ApplicationService;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

	@Resource
	private ApplicationMapper applicationMapper;
	@Resource
	private TeacherApplicationMapper teacherApplicationMapper;
	@Resource
	private TeacherMapper teacherMapper;

	public Application getApplicationByAid(Long aid) throws Exception {
		Application application = null;
		try {
			application = applicationMapper.selectByPrimaryKey(aid);
			if (application == null) {
				throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
			}
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ONE_RECORD_ERROR) + e.getMessage());
		}
		return application;
	}

	/** student */
	public void addApplicationByStudent(Application application) throws Exception {
		Calendar calendar = Calendar.getInstance();
		DateTime today = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
		DateTime start = new DateTime(application.getStartDate());
		DateTime over = new DateTime(application.getOverDate());

		if (start.isBefore(today)) { // 开始日期小于今天日期，抛出参数异常（ParameterException）
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_LT_TODAY));
		} else if (start.isAfter(over)) { // 开始日期大于结束日期，抛出参数异常（ParameterException）
			throw new ParameterJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_OVERDATE_LT_STARTDATE));
		}

		// 获取最新一次请假信息的请假结束日期
		Application lastApplication = applicationMapper.selectLastApplicationBySid(application.getSid());
		// 如果存在最新一次的请假信息
		if (lastApplication != null) {
			DateTime lastOverDate = new DateTime(lastApplication.getOverDate());
			// 如果已经申请请假的，那么不能继续申请请假
			// 判断是根据今天日期与最新一次请假信息的overDate来比较的，如果今天日期大于overDate，那么可以申请，否则不行
			if (lastOverDate.isEqual(today) || lastOverDate.isAfter(today)) {
				throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_CAN_NOT_APPLY));
			}
		}

		// 保存请假信息
		try {
			application.setCreateDate(today.toDate());
			application.setCountDay(Days.daysBetween(start, over).getDays() + 1);
			applicationMapper.insertSelective(application);
			teacherApplicationMapper.insert(new TeacherApplication(application.getTid(), application.getAid()));
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_ADD_ERROR)
					+ e.getMessage());
		}
	}

	public void deleteApplicationByStudent(Long aid) throws Exception {
		// 删除前先获取该条请假信息
		Application application = applicationMapper.selectByPrimaryKey(aid);
		// 判断是否已审核，是则不能删除，抛出异常
		if (application.getConfirmMark() != CustomConstant.STATE_DO_NOT_APPROVE) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_DELETE_FAILURE));

		}

		int result = 0;
		try {
			// 删除外键表的信息
			teacherApplicationMapper.deleteByPrimaryKey(new TeacherApplicationKey(application.getAid()));
			// 删除请假信息
			result = applicationMapper.deleteByPrimaryKey(application.getAid());
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_DELETE_ERROR)
					+ e.getMessage());
		}
		if (result == 0) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}

	}

	public void updateApplicationByStudent(Application application) throws Exception {
		Calendar calendar = Calendar.getInstance();
		DateTime today = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
		DateTime start = new DateTime(application.getStartDate());
		DateTime over = new DateTime(application.getOverDate());

		if (start.isBefore(today)) { // 开始日期小于今天日期，抛出参数异常（ParameterException）
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_LT_TODAY));
		} else if (start.isEqual(over) || start.isAfter(over)) { // 开始日期大于等于结束日期，抛出参数异常（ParameterException）
			throw new ParameterJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_OVERDATE_LT_STARTDATE));
		}

		// 修改请假信息
		int result = 0;
		try {
			application.setCountDay(Days.daysBetween(start, over).getDays());
			result = applicationMapper.updateByPrimaryKeySelective(application);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}
		if (result == 0) {
			throw new RuntimeException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}
	}

	public List<Application> getPageApplicationsBySid(Map<String, Object> params) throws Exception {
		List<Application> applications = null;

		try {
			applications = applicationMapper.selectPageApplicationsBySid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}

		return applications;
	}

	public Long getApplicationTotalCountBySid(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = applicationMapper.getApplicationTotalCountBySid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

	/** teacher */
	public List<Application> getPageStudentApplicationsByTid(Map<String, Object> params) throws Exception {
		List<Application> applications = null;
		try {
			applications = applicationMapper.selectPageStudentApplicationsByTid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR) + e.getMessage());
		}
		return applications;
	}

	public Long getStudentApplicationTotalCountByTid(Map<String, Object> params) throws Exception {
		Long totalCount = 0l;
		try {
			totalCount = applicationMapper.getStudentApplicationTotalCountByTid(params);
		} catch (Exception e) {
			throw new BusinessJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_TOTAL_COUNT_ERROR) + e.getMessage());
		}
		return totalCount;
	}

	public void updateApplicationByTeacher(Application application) throws Exception {
		/** 判断教师是否审批过该请假信息，是则不能进行审批 */
		TeacherApplication teacherApplication = teacherApplicationMapper.selectByPrimaryKey(new TeacherApplicationKey(application
				.getConfirmTid(), application.getAid()));
		if (teacherApplication.getHasConfirmed() == CustomConstant.ALREADY_CONFIRMED) {
			throw new ParameterJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_APPROVE_ALREADY_APPROVE));
		}

		/** 判断请假开始日期是否小于今天，是则不能审批 */
		Calendar calendar = Calendar.getInstance();
		DateTime today = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
		DateTime start = new DateTime(application.getStartDate());
		if (start.isBefore(today)) { // 开始日期小于今天日期，抛出参数异常（ParameterException）
			throw new ParameterJsonException(
					PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_LT_TODAY_CAN_NOT_APPROVE));
		}

		/** 判断是否将请假信息转给上一级审批 */
		boolean isForwardApprove = false;
		// 当前进行审批的教师
		Teacher teacher = teacherMapper.selectTeacherInfoByPrimaryKey(application.getConfirmTid());
		// 审批状态为同意，那么根据countDay和教师的等级排位决定是否将请假信息转给上一级审批
		if (application.getConfirmMark() == CustomConstant.STATE_AGREE) {
			// 获取审批教师等级排位
			int rank = teacher.getLevel().getRank();
			// 请假天数大于1天的，且审批人是中队长，需要转给大队长审批
			if (application.getCountDay() > PropertiesUtils.getCountDayValidationMessage(PropertiesUtils.DAY_A)
					&& rank == CustomConstant.SQUADRON_LEADER) {
				isForwardApprove = true;
			}
			// 请假天数大于3天的，且审批人是大队长，那么拟同意后要转给系主任审批
			if (application.getCountDay() > PropertiesUtils.getCountDayValidationMessage(PropertiesUtils.DAY_C)
					&& rank == CustomConstant.BATTALION_LEADER) {
				isForwardApprove = true;
			}
			// 请假天数大于7天的，且审批人是系主任，那么拟同意后要转给校领导审批
			if (application.getCountDay() > PropertiesUtils.getCountDayValidationMessage(PropertiesUtils.DAY_C)
					&& rank == CustomConstant.FACULTY_LEADER) {
				isForwardApprove = true;
			}
		}

		/** 进行数据的插入或删除 */
		// 标志插入或修改数据是否成功
		int resultA = 0, resultB = 0, resultC = 0;
		try {
			if (isForwardApprove) {
				// 审批状态改为拟同意
				application.setConfirmMark(CustomConstant.STATE_PROPOSED_AGREE);
				// 获取上一级的teacher对象
				Teacher superior = teacherMapper.selectSuperiorTeacherByPrimaryKey(application.getConfirmTid());
				// 如果上一级不为空，则转发给上一级
				if (superior != null) {
					teacherApplicationMapper.insertSelective(new TeacherApplication(superior.getTid(), application.getAid()));
				}
			}
			// 将teacher_application表中对应审批教师记录的hasFirmed置为1
			resultB = teacherApplicationMapper.updateByPrimaryKeySelective(new TeacherApplication(application.getConfirmTid(),
					application.getAid(), CustomConstant.ALREADY_CONFIRMED));
			// 修改审批信息
			application.setConfirmDate(today.toDate());
			application.setConfirmName(teacher.getTname());
			resultC = applicationMapper.updateByPrimaryKeySelective(application);
		} catch (Exception e) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_UPDATE_ERROR)
					+ e.getMessage());
		}

		/** 判断插入或修改数据是否成功，失败就抛出异常 */
		if (resultB == 0 || resultC == 0) {
			throw new RuntimeException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		} else if (isForwardApprove && resultA == 0) {
			throw new RuntimeException(PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_RECORD_NOT_EXIST));
		}

	}

	/** manager */
	// public List<Application> getPageApplications(PageInfo pageInfo) throws
	// Exception {
	// List<Application> applications = null;
	// try {
	// applications = applicationMapper.selectPageApplication(pageInfo);
	// } catch (Exception e) {
	// throw new BusinessJsonException(
	// PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_PAGE_RECORD_ERROR)
	// + e.getMessage());
	// }
	// return applications;
	// }
	//
	// public List<Application> getAllApplications() throws Exception {
	// List<Application> applications = null;
	// try {
	// applications = applicationMapper.selectAllApplication();
	// } catch (Exception e) {
	// throw new BusinessJsonException(
	// PropertiesUtils.getValidationMessage(PropertiesUtils.BUSINESS_SELECT_ALL_RECORD_ERROR)
	// + e.getMessage());
	// }
	// return applications;
	// }

}