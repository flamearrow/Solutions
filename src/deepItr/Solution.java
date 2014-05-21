package deepItr;


//"Program an iterator for a List which may include nodes or List  
// i.e.  * {0,{1,2}, 3 ,{4,{5, 6}}} Iterator returns 0 - 1 - 2 - 3 - 4 - 5 - 6"

// the idea is to recursively call itr.getNext() when there're nested items
//  The iterator would have a back array of items and an index indicating what the next item is
//    also the iterator would have a field 'curItr' if current item is an array
//  getNext():
//   when array[index] is num, getNext() returns this number and increment index
//   when array[index] is array, getNext() needs to check if curItr hasNext(), 
//  	if curItr hasNext, then just return curItr.getNext(), note after this call, 
//			curItr might run out of items, we need to update index and update curItr
//      if curItr doesn't have next, just update index and call getNext() again.
//  hasNext():
//   if array[index] is num, return true
//   otherwise return curItr.hasNext()
//
//  The idea is hasNext() never changes anything, getNext() in charge of point index and curItr to the correct place
public class Solution {
	public static void main(String[] args) throws MLGB {
		Item[] items = new Item[4];
		Item[] items1 = new Item[2];
		items1[0] = new Item(1);
		items1[1] = new Item(2);

		Item[] items2 = new Item[2];
		items2[0] = new Item(4);

		Item[] items3 = new Item[2];
		items3[0] = new Item(5);
		items3[1] = new Item(6);

		items2[1] = new Item(items3);

		items[0] = new Item(0);
		items[1] = new Item(items1);
		items[2] = new Item(3);
		items[3] = new Item(items2);

		Item root = new Item(items);
		ItemItr itr = new ItemItrImpl(root);
		while (itr.hasNext())
			System.out.println(itr.getNext());
	}
}

interface ItemItr {
	boolean hasNext();

	int getNext() throws MLGB;
}

// suppose no empty array
class ItemItrImpl implements ItemItr {
	Item _root;
	int index;
	ItemItr curItr = null;

	public ItemItrImpl(Item root) {
		_root = root;
		index = 0;
		if (!root.items[0].isNum) {
			curItr = new ItemItrImpl(root.items[0]);
		}
	}

	@Override
	public boolean hasNext() {
		if (index >= _root.items.length)
			return false;
		if (_root.items[index].isNum)
			return true;
		// the current item is an array
		else {
			return curItr.hasNext();
		}
	}

	// getNext() moves cursor and creates new itr if necessary
	@Override
	public int getNext() throws MLGB {
		try {
			if (_root.items[index].isNum) {
				index++;
				// if we jump to an array, update curItr
				updateCurItr();
				return _root.items[index - 1].val;
			} else {
				if (curItr.hasNext()) {
					int ret = curItr.getNext();
					// here we need to check if curItr has next
					// it if doesn't then we need to move index and update itr 
					if (!curItr.hasNext()) {
						index++;
						updateCurItr();
					}
					return ret;
				} else {
					index++;
					updateCurItr();
					return getNext();
				}
			}
		} catch (Exception e) {
			throw new MLGB();
		}
	}

	private void updateCurItr() {
		if (index < _root.items.length && !_root.items[index].isNum) {
			curItr = new ItemItrImpl(_root.items[index]);
		}
	}
}

class MLGB extends Exception {

	private static final long serialVersionUID = 1L;

}

class Item {
	boolean isNum;
	Item[] items;
	int val;

	public Item(int val) {
		isNum = true;
		this.val = val;
	}

	public Item(Item[] items) {
		isNum = false;
		this.items = items;
	}
}