package com.yzl.pojo;

import java.util.List;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlMonitoringstatistics {
	
	private String ename;
	
	private String tname;
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	private String county;
	
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	private List<YzlDistrict> countys;
	
	public List<YzlDistrict> getCountys() {
		return countys;
	}

	public void setCountys(List<YzlDistrict> countys) {
		this.countys = countys;
	}

	private String city;
	
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
     * ���ͳ�Ʊ�
     */
    private Long id;

    /**
     * �������
     */
    private Long did;

    /**
     * ���̱��
     */
    private Long eid;

    /**
     * ������
     */
    private String tid;

    /**
     * ʱ��
     */
    private String time;

    /**
     * �Լ��ϱ����
     */
    private String inspectionreportarea;

    /**
     * ��ʵ���
     */
    private String verifyarea;

    /**
     * ��ʵ�ϸ����
     */
    private String certifiedarea;

    /**
     * ��ҵ������
     */
    private String jobdesignarea;

    /**
     * ����ҵ���ʩ�����
     */
    private String designconstructionaccordingoperation;

    /**
     * �е������
     */
    private String filesize;

    /**
     * ��չ�ؼ��Լ����
     */
    private String countyinspectionarea;

    /**
     * ����������
     */
    private String designforestarea;

    /**
     * ��ʵ�������
     */
    private String verifyforestarea;

    /**
     * �������
     */
    private String barearea;

    /**
     * �ܻ����
     */
    private String managementarea;

    /**
     * �����ʵ��
     */
    private String areaverificationrate;

    /**
     * ��ʵ����ϸ���
     */
    private String verifyareapassrate;

    /**
     * �ϱ�����ϸ���
     */
    private String reportareaqualificationrate;

    /**
     * �����ϱ����������
     */
    private String reportretentionrateyear;

    /**
     * ���ֵ����ϱ��ϸ����
     */
    private String afforestationreportseligibleyear;

    /**
     * ȫ���Լ�ϸ����
     */
    private String countyselfinspectionarea;

    /**
     * �����ʵ���
     */
    private String calculatedverificationarea;

    /**
     * ������ɺϸ����
     */
    private String calculatequalifiedarea;

    /**
     * �ƻ�����
     */
    private String scheduledtask;

    /**
     * ���������
     */
    private String taskcompletionrate;

    /**
     * ��ҵ�����
     */
    private String jobdesignrate;

    /**
     * �����ʩ����
     */
    private String accordingdesignconstructionrate;

    /**
     * ������
     */
    private String byinputtingrate;

    /**
     * �Բ���
     */
    private String selfcheckingrate;

    /**
     * �Լ���
     */
    private String selfcheckingsrate;

    /**
     * ������
     */
    private String filerate;

    /**
     * ������
     */
    private String raisingrates;

    /**
     * ������
     */
    private String afforestationrate;

    /**
     * �ܻ���
     */
    private String themanagementrate;

    private String mark;

    /**
     * �Ƿ��е������
     */
    private String sfyda;

    /**
     * �Ƿ���
     */
    private String sffy;

    /**
     * �Ƿ�ܻ�
     */
    private String sfgh;

    /**
     * �Ƿ�չ�ؼ��Լ�
     */
    private String sfzj;

    /**
     * ���ַ�ʽ
     */
    private String ylfs;

    /**
     * �������
     */
    private String clmj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getInspectionreportarea() {
        return inspectionreportarea;
    }

    public void setInspectionreportarea(String inspectionreportarea) {
        this.inspectionreportarea = inspectionreportarea == null ? null : inspectionreportarea.trim();
    }

    public String getVerifyarea() {
        return verifyarea;
    }

    public void setVerifyarea(String verifyarea) {
        this.verifyarea = verifyarea == null ? null : verifyarea.trim();
    }

    public String getCertifiedarea() {
        return certifiedarea;
    }

    public void setCertifiedarea(String certifiedarea) {
        this.certifiedarea = certifiedarea == null ? null : certifiedarea.trim();
    }

    public String getJobdesignarea() {
        return jobdesignarea;
    }

    public void setJobdesignarea(String jobdesignarea) {
        this.jobdesignarea = jobdesignarea == null ? null : jobdesignarea.trim();
    }

    public String getDesignconstructionaccordingoperation() {
        return designconstructionaccordingoperation;
    }

    public void setDesignconstructionaccordingoperation(String designconstructionaccordingoperation) {
        this.designconstructionaccordingoperation = designconstructionaccordingoperation == null ? null : designconstructionaccordingoperation.trim();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public String getCountyinspectionarea() {
        return countyinspectionarea;
    }

    public void setCountyinspectionarea(String countyinspectionarea) {
        this.countyinspectionarea = countyinspectionarea == null ? null : countyinspectionarea.trim();
    }

    public String getDesignforestarea() {
        return designforestarea;
    }

    public void setDesignforestarea(String designforestarea) {
        this.designforestarea = designforestarea == null ? null : designforestarea.trim();
    }

    public String getVerifyforestarea() {
        return verifyforestarea;
    }

    public void setVerifyforestarea(String verifyforestarea) {
        this.verifyforestarea = verifyforestarea == null ? null : verifyforestarea.trim();
    }

    public String getBarearea() {
        return barearea;
    }

    public void setBarearea(String barearea) {
        this.barearea = barearea == null ? null : barearea.trim();
    }

    public String getManagementarea() {
        return managementarea;
    }

    public void setManagementarea(String managementarea) {
        this.managementarea = managementarea == null ? null : managementarea.trim();
    }

    public String getAreaverificationrate() {
        return areaverificationrate;
    }

    public void setAreaverificationrate(String areaverificationrate) {
        this.areaverificationrate = areaverificationrate == null ? null : areaverificationrate.trim();
    }

    public String getVerifyareapassrate() {
        return verifyareapassrate;
    }

    public void setVerifyareapassrate(String verifyareapassrate) {
        this.verifyareapassrate = verifyareapassrate == null ? null : verifyareapassrate.trim();
    }

    public String getReportareaqualificationrate() {
        return reportareaqualificationrate;
    }

    public void setReportareaqualificationrate(String reportareaqualificationrate) {
        this.reportareaqualificationrate = reportareaqualificationrate == null ? null : reportareaqualificationrate.trim();
    }

    public String getReportretentionrateyear() {
        return reportretentionrateyear;
    }

    public void setReportretentionrateyear(String reportretentionrateyear) {
        this.reportretentionrateyear = reportretentionrateyear == null ? null : reportretentionrateyear.trim();
    }

    public String getAfforestationreportseligibleyear() {
        return afforestationreportseligibleyear;
    }

    public void setAfforestationreportseligibleyear(String afforestationreportseligibleyear) {
        this.afforestationreportseligibleyear = afforestationreportseligibleyear == null ? null : afforestationreportseligibleyear.trim();
    }

    public String getCountyselfinspectionarea() {
        return countyselfinspectionarea;
    }

    public void setCountyselfinspectionarea(String countyselfinspectionarea) {
        this.countyselfinspectionarea = countyselfinspectionarea == null ? null : countyselfinspectionarea.trim();
    }

    public String getCalculatedverificationarea() {
        return calculatedverificationarea;
    }

    public void setCalculatedverificationarea(String calculatedverificationarea) {
        this.calculatedverificationarea = calculatedverificationarea == null ? null : calculatedverificationarea.trim();
    }

    public String getCalculatequalifiedarea() {
        return calculatequalifiedarea;
    }

    public void setCalculatequalifiedarea(String calculatequalifiedarea) {
        this.calculatequalifiedarea = calculatequalifiedarea == null ? null : calculatequalifiedarea.trim();
    }

    public String getScheduledtask() {
        return scheduledtask;
    }

    public void setScheduledtask(String scheduledtask) {
        this.scheduledtask = scheduledtask == null ? null : scheduledtask.trim();
    }

    public String getTaskcompletionrate() {
        return taskcompletionrate;
    }

    public void setTaskcompletionrate(String taskcompletionrate) {
        this.taskcompletionrate = taskcompletionrate == null ? null : taskcompletionrate.trim();
    }

    public String getJobdesignrate() {
        return jobdesignrate;
    }

    public void setJobdesignrate(String jobdesignrate) {
        this.jobdesignrate = jobdesignrate == null ? null : jobdesignrate.trim();
    }

    public String getAccordingdesignconstructionrate() {
        return accordingdesignconstructionrate;
    }

    public void setAccordingdesignconstructionrate(String accordingdesignconstructionrate) {
        this.accordingdesignconstructionrate = accordingdesignconstructionrate == null ? null : accordingdesignconstructionrate.trim();
    }

    public String getByinputtingrate() {
        return byinputtingrate;
    }

    public void setByinputtingrate(String byinputtingrate) {
        this.byinputtingrate = byinputtingrate == null ? null : byinputtingrate.trim();
    }

    public String getSelfcheckingrate() {
        return selfcheckingrate;
    }

    public void setSelfcheckingrate(String selfcheckingrate) {
        this.selfcheckingrate = selfcheckingrate == null ? null : selfcheckingrate.trim();
    }

    public String getSelfcheckingsrate() {
        return selfcheckingsrate;
    }

    public void setSelfcheckingsrate(String selfcheckingsrate) {
        this.selfcheckingsrate = selfcheckingsrate == null ? null : selfcheckingsrate.trim();
    }

    public String getFilerate() {
        return filerate;
    }

    public void setFilerate(String filerate) {
        this.filerate = filerate == null ? null : filerate.trim();
    }

    public String getRaisingrates() {
        return raisingrates;
    }

    public void setRaisingrates(String raisingrates) {
        this.raisingrates = raisingrates == null ? null : raisingrates.trim();
    }

    public String getAfforestationrate() {
        return afforestationrate;
    }

    public void setAfforestationrate(String afforestationrate) {
        this.afforestationrate = afforestationrate == null ? null : afforestationrate.trim();
    }

    public String getThemanagementrate() {
        return themanagementrate;
    }

    public void setThemanagementrate(String themanagementrate) {
        this.themanagementrate = themanagementrate == null ? null : themanagementrate.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getSfyda() {
        return sfyda;
    }

    public void setSfyda(String sfyda) {
        this.sfyda = sfyda == null ? null : sfyda.trim();
    }

    public String getSffy() {
        return sffy;
    }

    public void setSffy(String sffy) {
        this.sffy = sffy == null ? null : sffy.trim();
    }

    public String getSfgh() {
        return sfgh;
    }

    public void setSfgh(String sfgh) {
        this.sfgh = sfgh == null ? null : sfgh.trim();
    }

    public String getSfzj() {
        return sfzj;
    }

    public void setSfzj(String sfzj) {
        this.sfzj = sfzj == null ? null : sfzj.trim();
    }

    public String getYlfs() {
        return ylfs;
    }

    public void setYlfs(String ylfs) {
        this.ylfs = ylfs == null ? null : ylfs.trim();
    }

    public String getClmj() {
        return clmj;
    }

    public void setClmj(String clmj) {
        this.clmj = clmj == null ? null : clmj.trim();
    }
}