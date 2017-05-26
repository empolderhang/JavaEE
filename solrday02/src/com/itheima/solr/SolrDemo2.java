package com.itheima.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrDemo2 {
	@Test
	public void demo1()throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery("小黄人");
		//设置过滤条件
		solrQuery.setFilterQueries("product_price:[1 TO 100]");
		//排序条件
		solrQuery.setSort("product_price", ORDER.desc);
		//分页出路
		solrQuery.setStart(0);
		solrQuery.setRows(10);
		solrQuery.setFields("id","product_name","product_price","product_catalog_name","product_picture");
		//设置默认搜索域
		solrQuery.set("df","product_keywords");
		//高亮显示
		solrQuery.addHighlightField("product_name");
		//高亮显示的前缀
		solrQuery.setHighlightSimplePre("<em>");
		//高亮显示的后缀
		solrQuery.setHighlightSimplePost("</em>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		//取查询的结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("商品的总数量 :"+solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			//取高亮显示
			String productName = "";
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			//判断是否有高亮内容
			if (null != list) {
				productName = list.get(0);
			} else {
				productName = (String) solrDocument.get("product_name");
			}
			
			System.out.println(productName);
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_picture"));
			
		}
	}
}
