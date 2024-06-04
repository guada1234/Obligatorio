/**
 * 
 */
package prog_2_adts.src.uy.edu.um.adt.binarytree;

import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.queue.EmptyQueueException;
import prog_2_adts.src.uy.edu.um.adt.queue.MyQueue;

public class SearchBinaryTreeImpl<K,T> implements BinaryTree<K,T> {
	private TreeNode<K,T> root;

	public TreeNode<K,T> getRoot(){
		return this.root;
	}

	@Override
	public T serch(K key) throws EmptyTree, InvalidKey {
		TreeNode<K,T> res;
		if (this.root != null) {
			res = this.root.findNode(key);
		} else {
			throw new EmptyTree();
		}
		return res.getData();
	}

	@Override
	public TreeNode<K,T> serchNode(K key) throws EmptyTree, InvalidKey {
		TreeNode<K,T> res;
		if (this.root != null) {
			res = this.root.findNode(key);
		} else {
			throw new EmptyTree();
		}
		return res;
	}

	@Override
	public void add(K key, T data) throws InvalidKey {
		TreeNode<K,T> add = new TreeNode<>(key, data);
		TreeNode<K,T> parent;
		if (this.root != null) {
			parent = this.root.findFreeParent(key);
			if (parent.compareTo(key) > 0) {
				parent.setLeftChild(add);
			} else {
				parent.setRightChild(add);
			}
		} else {
			this.root = add;
		}
	}

	@Override
	public void addNode(TreeNode<K, T> add) throws InvalidKey{
		if (this.root != null) {
			TreeNode<K,T> parent = this.root.findFreeParent(add.getKey());
			if (parent.compareTo(add.getKey()) > 0) {
				parent.setLeftChild(add);
			} else {
				parent.setRightChild(add);
			}
		} else {
			this.root = add;
		}
	}

	@Override
	public void delete(K key) throws InvalidKey, EmptyTree{
		if (this.root != null) {
			TreeNode<K,T> del = this.serchNode(key);
			TreeNode<K,T> parent = this.root.findParent(key);
			if (parent.getLeftChild() == del) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);
			}
			MyLinkedListImpl<TreeNode<K,T>> list = new MyLinkedListImpl<TreeNode<K,T>>();
			del.getChildList(list);
			list.remove(list.get(0));
			for (int i = 0; i < list.size(); i ++) {
				list.get(i).setRightChild(null);
				list.get(i).setLeftChild(null);
				this.addNode(list.get(i));
			}
		} else {
			throw new EmptyTree();
		}
	}

	public void inOrder() throws EmptyTree {
		MyLinkedListImpl<T> list = new MyLinkedListImpl<>();
		if (this.root != null) {
			this.root.inOrder(list);
		} else {
			throw new EmptyTree();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == this.root.getData()) {
				System.out.println(list.get(i) + " [R]");
			} else {
				System.out.println(list.get(i));
			}
		}
	}
	public void preOrder() throws EmptyTree {
		MyLinkedListImpl<T> list = new MyLinkedListImpl<>();
		if (this.root != null) {
			this.root.preOrder(list);
		} else {
			throw new EmptyTree();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == this.root.getData()) {
				System.out.println(list.get(i) + " [R]");
			} else {
				System.out.println(list.get(i));
			}
		}
	}
	public void postOrder() throws EmptyTree {
		MyLinkedListImpl<T> list = new MyLinkedListImpl<>();
		if (this.root != null) {
			this.root.postOrder(list);
		} else {
			throw new EmptyTree();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == this.root.getData()) {
				System.out.println(list.get(i) + " [R]");
			} else {
				System.out.println(list.get(i));
			}
		}
	}

	public TreeNode<K,T> getMax() throws EmptyTree {
		TreeNode<K,T> maximum;
		if (this.root != null) {
			MyLinkedListImpl<TreeNode<K,T>> list = new MyLinkedListImpl<>();
			this.root.getChildList(list);
			maximum = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).compareTo(maximum.getKey()) > 0) {
					maximum = list.get(i);
				}
			}
		} else {
			throw new EmptyTree();
		}
		return maximum;
	}

	public TreeNode<K,T> getMin() throws EmptyTree {
		TreeNode<K,T> minimum;
		if (this.root != null) {
			MyLinkedListImpl<TreeNode<K,T>> list = new MyLinkedListImpl<TreeNode<K,T>>();
			this.root.getChildList(list);
			minimum = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).compareTo(minimum.getKey()) < 0) {
					minimum = list.get(i);
				}
			}
		} else {
			throw new EmptyTree();
		}
		return minimum;
	}

	public void levelRouting() throws EmptyTree, EmptyQueueException {
		if (this.root != null) {
			MyQueue<TreeNode<K,T>> queue = new MyLinkedListImpl<TreeNode<K, T>>();
			queue.enqueue(this.root);
			this.root.levelRouting(queue);
		} else {
			throw new EmptyTree();
		}
	}


}