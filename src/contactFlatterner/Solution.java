package contactFlatterner;

import java.util.LinkedList;
import java.util.List;

//����ôһ��class Contact��������һ��String��name����һ��List<String>
//װ��email address�������Contact�е�address����һ��listװ������Ϊһ�����п�
//���ж��email�����ڸ���an array of Contact������
//#1 John [john@gmail.com]
//#2 Mary [mary@gmail.com]
//#3 John [john@yahoo.com]
//#4 John [john@gmail.com, john@yahoo.com, john@hotmail.com]
//#5 Bob [bob@gmail.com]
//��������֪�����email address��ͬ�Ļ�����ô��˵����ͬһ���ˣ�����Ҫ�����Ǹ�
//����Щemail address����ͬһ��contact��group����������һ��List<List<Contact>>
//���������ǾͿ���֪��#1��#3��#4��ͬһ���ˡ�ע�ⲻ�ܸ���������group����Ϊ�п�
//������ͬ���ֵ��ˣ�����ͬһ�����п����в�ͬ��������ע��֮��ġ�
public class Solution {
	// create a graph with emails and contacts, then create a internal Node that contains email and Contact
	// when iterating through the input list, create a Set of emails and link contacts to the emails.
	// Then for each email node in the Set, do a bfs, group all contacts as one list, then these guys would be the same ppl
	//  repeat this until the set becomes null
	// This would be a O(m+n) solution where m is # of contacts and n is number of emails
}

class Contact{
	String name;
	List<String> emails;
	public Contact(String name) {
		this.name = name;
		emails = new LinkedList<String>();
	}
}
