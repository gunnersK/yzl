package com.yzl.utils.enums;

public enum UserEnum {
	
	//��ǰ�ѵ�¼�û�
	LOGIN_USER("loginUser"),
	
	;
        private String status;
        
        private UserEnum(String status) {
        	this.status=status;
        }

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
        
}
