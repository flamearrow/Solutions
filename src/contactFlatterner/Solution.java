package contactFlatterner;

import java.util.LinkedList;
import java.util.List;

//有这么一个class Contact，里面有一个String的name，和一个List<String>
//装着email address，是这个Contact有的address，用一个list装着是因为一个人有可
//能有多个email，现在给你an array of Contact，比如
//#1 John [john@gmail.com]
//#2 Mary [mary@gmail.com]
//#3 John [john@yahoo.com]
//#4 John [john@gmail.com, john@yahoo.com, john@hotmail.com]
//#5 Bob [bob@gmail.com]
//现在我们知道如果email address相同的话，那么就说明是同一个人，现在要做的是根
//据这些email address，把同一个contact给group起来，生成一个List<List<Contact>>
//，比如我们就可以知道#1，#3，#4是同一个人。注意不能根据名字来group，因为有可
//能有相同名字的人，或者同一个人有可能有不同的名字来注册之类的。
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
