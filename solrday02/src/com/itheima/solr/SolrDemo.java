package com.itheima.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrDemo {

	@Test
	public void addDocument()throws Exception{
//	1）创建一个工程，添加jar包。
//		solr-solrj-4.10.3.jar
//		\solr-4.10.3\dist\solrj-lib目录下的jar包
//		\solr-4.10.3\example\lib\ext目录下的jar包
//	2）创建一个SolrServer对象，相当于和服务端建立连接。需要使用HttpSolrServer类。
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
//	3）创建一个文档对象SolrInputDocument
		SolrInputDocument document = new SolrInputDocument();
//	4）向文档中添加域
//		每个文件中必须有id域
//		每个域必须在schema.xml中定义。
		document.addField("id", "4");
		document.addField("title", "新添加的文档", 8f);
//	5）把文档对象写入索引库
		solrServer.add(document);
//	6）提交
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument()throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		solrServer.deleteById("4");
		solrServer.commit();
	}
	public static void main(String[] args) {
		System.out.println("say hello git.......");
	}
}
