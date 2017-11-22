package io.github.seccoding.web.pager;

import io.github.seccoding.web.pager.explorer.ClassicPageExplorer;
import io.github.seccoding.web.pager.explorer.ListPageExplorer;
import io.github.seccoding.web.pager.explorer.PageExplorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerTest {

	public static void main(String[] args) {
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(17);
		pager.setTotalArticleCount(220);
		
		// 기본 타입
		String page1 = pager.makePageExplorer(ListPageExplorer.class)
							.setData("pageNo", "[@]", "이전", "다음", "form")
							.make();
		// 기본 타입
		String page2 = pager.makePageExplorer(ClassicPageExplorer.class)
							.setData("pageNo", "[@]", "이전", "다음", "form")
							.make();
		
		// UI 변경
		String page3 = pager.makePageExplorer(ListPageExplorer.class)
							.setData("pageNo", "[@]", "이전", "다음", "form")
							.prevGroup((prevGroupPageNumber, prev) -> {
								return "<div href=\"javascript:movePage('" + prevGroupPageNumber + "')\">" + prev + "</div>";
							})
							.nextGroup((nextGroupPageNumber, next) -> {
								return "<div href=\"javascript:movePage('" + nextGroupPageNumber + "')\">" + next + "</div>";
							})
							.pages((pageNo, pageFormat) -> {
								return "<div onclick=\"javascript:movePage('" + pageNo + "')\">" + pageFormat + "</div>";
							})
							.highlight(pageNo -> {
								return "<div>"+pageNo+"</div>";
							})
							.make();
		
		// UI 변경
		String page4 = pager.makePageExplorer(ClassicPageExplorer.class)
							.setData("pageNo", "[@]", "이전", "다음", "form")
							.prevGroup((prevGroupPageNumber, prev) -> {
								return "<div href=\"javascript:movePage('" + prevGroupPageNumber + "')\">" + prev + "</div>";
							})
							.nextGroup((nextGroupPageNumber, next) -> {
								return "<div href=\"javascript:movePage('" + nextGroupPageNumber + "')\">" + next + "</div>";
							})
							.pages((pageNo, pageFormat) -> {
								return "<div onclick=\"javascript:movePage('" + pageNo + "')\">" + pageFormat + "</div>";
							})
							.highlight(pageNo -> {
								return "<div>"+pageNo+"</div>";
							})
							.make();
		
		System.out.println(page1);
		System.out.println(page2);
		System.out.println(page3);
		System.out.println(page4);

		List<TestModel> data = Arrays.asList(new TestModel("JMC"), new TestModel("JMS"));

		PageExplorer pageExplorer = pager.makePageExplorer(ClassicPageExplorer.class).setList(data);

		List<TestModel> data2 = pageExplorer.getList();
		data2.forEach(tm -> System.out.println(tm.getName()));

		System.out.println(pageExplorer.setData("pageNo", "@", "P", "N", "F").make());


		
	}
	
}
