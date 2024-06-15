/**
 * 
 */
package prog_2_adts.src.uy.edu.um.adt.binarytree;

import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import prog_2_adts.src.uy.edu.um.adt.queue.EmptyQueueException;
import prog_2_adts.src.uy.edu.um.adt.queue.MyQueue;

public class SearchBinaryTreeImpl<K extends Comparable<K>, T> implements BinaryTree<K , T>{
	private TreeNode<K,T> root;

	public TreeNode<K,T> getRoot(){
		return this.root;
	}
	@Override
	public int size(){
		return root.size(root);
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
	public void delete(K key) throws EmptyTree, InvalidKey {
		TreeNode<K, T> nodeToDelete = findNode(root, key);
		if (nodeToDelete == null) {
			throw new InvalidKey();
		}

		// Caso 1: El nodo a eliminar es una hoja (no tiene hijos)
		if (nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() == null) {
			removeNode(nodeToDelete);
			return;
		}

		// Caso 2: El nodo a eliminar tiene un hijo izquierdo
		if (nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() == null) {
			replaceNode(nodeToDelete, nodeToDelete.getLeftChild());
			return;
		}

		// Caso 3: El nodo a eliminar tiene un hijo derecho
		if (nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() != null) {
			replaceNode(nodeToDelete, nodeToDelete.getRightChild());
			return;
		}

		// Caso 4: El nodo a eliminar tiene dos hijos
		TreeNode<K, T> successor = findSuccessor(nodeToDelete);
		replaceNode(nodeToDelete, successor);
	}

	// Método auxiliar para encontrar el nodo que se va a eliminar
	private TreeNode<K, T> findNode(TreeNode<K, T> node, K key) {
		if (node == null) {
			return null;
		}
		if (node.getKey().compareTo(key) == 0) {
			return node;
		}
		TreeNode<K, T> left = findNode(node.getLeftChild(), key);
		if (left != null) {
			return left;
		}
		return findNode(node.getRightChild(), key);
	}

	// Método auxiliar para eliminar un nodo del árbol
	private void removeNode(TreeNode<K, T> node) {
		if (node == root) {
			root = null;
		} else {
			TreeNode<K, T> parent = findParent(node);
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);
			}
		}
	}

	// Método auxiliar para reemplazar un nodo con otro
	private void replaceNode(TreeNode<K, T> node, TreeNode<K, T> newNode) {
		if (node == root) {
			root = newNode;
		} else {
			TreeNode<K, T> parent = findParent(node);
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(newNode);
			} else {
				parent.setRightChild(newNode);
			}
		}
	}

	// Método auxiliar para encontrar el padre de un nodo
	private TreeNode<K, T> findParent(TreeNode<K, T> node) {
		if (node == root) {
			return null;
		}
		TreeNode<K, T> parent = root;
		while (true) {
			if (parent.getLeftChild() == node || parent.getRightChild() == node) {
				return parent;
			}
			if (parent.getKey().compareTo(node.getKey()) < 0) {
				parent = parent.getRightChild();
			} else {
				parent = parent.getLeftChild();
			}
		}
	}

	// Método auxiliar para encontrar el sucesor de un nodo
	private TreeNode<K, T> findSuccessor(TreeNode<K, T> node) {
		TreeNode<K, T> successor = node.getRightChild();
		while (successor.getLeftChild() != null) {
			successor = successor.getLeftChild();
		}
		return successor;
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