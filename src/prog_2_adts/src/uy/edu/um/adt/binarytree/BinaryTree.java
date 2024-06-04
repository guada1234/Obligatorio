/**
 * 
 */
package prog_2_adts.src.uy.edu.um.adt.binarytree;


public interface BinaryTree<K, T> {
	public T serch(K key) throws EmptyTree, InvalidKey;

	TreeNode<K, T> serchNode(K key) throws EmptyTree, InvalidKey;

	public void add(K key, T data) throws InvalidKey;

	void addNode(TreeNode<K, T> add) throws InvalidKey;

	public void delete(K key) throws InvalidKey, EmptyTree;
	public TreeNode<K,T> getMin() throws EmptyTree;
}
