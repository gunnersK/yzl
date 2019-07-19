package com.yzl.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
		//��ҳ��
		private int totalPage;
		//�ܼ�¼��
		private int totalCount;
		//��ǰҳ
		private int currentPage;
		//ÿҳ��ʾ��¼��
		private int pageSize;
		
		private List<T> list=new ArrayList<T>(); //ÿҳҪ��ʾ���������� 

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public List<T> getList() {
			return list;
		}

		public void setList(List<T> list) {
			this.list = list;
		}
		

}
