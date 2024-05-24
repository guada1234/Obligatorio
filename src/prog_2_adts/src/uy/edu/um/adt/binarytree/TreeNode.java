/**
 * 
 */
package prog_2_adts.src.uy.edu.um.adt.binarytree;

import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.queue.EmptyQueueException;
import prog_2_adts.src.uy.edu.um.adt.queue.MyQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author pegardan
 *
 */
public class TreeNode<K,T> implements Comparable<K>{
	private T data;
	private K key;
	private TreeNode<K,T> leftChild;
	private TreeNode<K,T> rightChild;
	public TreeNode(K key, T data) {
		this.data = data;
		this.key = key;
	}
	@Override
	public int compareTo(K otherKey) {
		return (int)(this.key) - (int)(otherKey);
	}
	public K getKey() {
		return this.key;
	}
	public T getData() {
		return this.data;
	}
	public TreeNode<K, T> getLeftChild() {
		return this.leftChild;
	}
	public TreeNode<K,T> getRightChild() {
		return this.rightChild;
	}
	public void setLeftChild(TreeNode<K, T> left) {
		this.leftChild = left;
	}
	public void setRightChild(TreeNode<K,T> right) {
		this.rightChild = right;
	}
	public TreeNode<K, T> findNode(K key) throws InvalidKey{
		TreeNode<K,T> res = null;
		if (this.key == key) {
			res = this;
		} else {
			if (this.compareTo(key) > 0) {
				if (this.leftChild != null) {
					res = this.leftChild.findNode(key);
				}
			} else {
				if (this.rightChild != null) {
					res = this.rightChild.findNode(key);
				}
			}
		}
		if (res == null) {
			throw new InvalidKey();
		}
		return res;
	}
	public TreeNode<K, T> findFreeParent(K key) throws InvalidKey {
		TreeNode<K,T> parent = null;
		if (this.compareTo(key) > 0) {
			if (this.leftChild == null) {
				parent = this;
			} else {
				parent = this.leftChild.findFreeParent(key);
			}
		} else if (this.compareTo(key) < 0) {
			if (this.rightChild == null) {
				parent = this;
			} else {
				parent = this.rightChild.findFreeParent(key);
			}
		} else {
			throw new InvalidKey();
		}
		return parent;
	}
	public void inOrder(MyLinkedListImpl<T> list) {
		if (this.leftChild != null) {
			this.leftChild.inOrder(list);
		}
		list.add(this.data);
		if (this.rightChild != null) {
			this.rightChild.inOrder(list);
		}
	}
	public void preOrder(MyLinkedListImpl<T> list) {
		list.add(this.data);
		if (this.leftChild != null) {
			this.leftChild.preOrder(list);
		}
		if (this.rightChild != null) {
			this.rightChild.preOrder(list);
		}
	}
	public void postOrder(MyLinkedListImpl<T> list) {
		if (this.leftChild != null) {
			this.leftChild.postOrder(list);
		}
		if (this.rightChild != null) {
			this.rightChild.postOrder(list);
		}
		list.add(this.data);
	}
	public TreeNode<K,T> findParent(K key) throws InvalidKey {
		TreeNode<K,T> parent = null;
		if (this.leftChild != null && this.leftChild.key == key) {
			parent = this;
		}
		if (this.rightChild != null && this.rightChild.key == key) {
			parent = this;
		}
		if (parent == null) {
			if (this.compareTo(key) > 0) {
				if (this.leftChild != null) {
					parent = this.leftChild.findParent(key);
				}
			} else {
				if (this.rightChild != null) {
					parent = this.rightChild.findParent(key);
				}
			}
		}
		if (parent == null) {
			throw new InvalidKey();
		}
		return parent;
	}
	public void getChildList(MyLinkedListImpl<TreeNode<K,T>> list) {
		list.add(this);
		if (this.leftChild != null) {
			this.leftChild.getChildList(list);
		}
		if (this.rightChild != null) {
			this.rightChild.getChildList(list);
		}
	}
	public void levelRouting(MyQueue<TreeNode<K,T>> queue) throws EmptyQueueException {
		TreeNode<K,T> temp = null;
		int originalSize = queue.size();
		boolean haySiguienteNivel = false;
		for (int i = 0; i < originalSize; i++) {
			temp = (queue.dequeue());
			System.out.print(temp.data + " ");
			if (temp.leftChild != null) {
				queue.enqueue(temp.leftChild);
				haySiguienteNivel = true;
			}
			if (temp.rightChild != null) {
				queue.enqueue(temp.rightChild);
				haySiguienteNivel = true;
			}
			if (i == originalSize - 1) {
				System.out.println();
			}
		}
		if (haySiguienteNivel) {
			temp.levelRouting(queue);
		}

	}
}



