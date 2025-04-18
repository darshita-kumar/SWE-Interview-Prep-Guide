# Trees

## Morris Tree Traversal
- Form a temp thread from the inorder succcessor of a node to it's parent.
- Ref: https://takeuforward.org/data-structure/morris-inorder-traversal-of-a-binary-tree/
- Ref: https://takeuforward.org/data-structure/morris-preorder-traversal-of-a-binary-tree/
- Helps obtain tree traversal with a SC of O(1)
- Code for inorder:
  ```java
          class Solution {
            public List<Integer> inorderTraversal(TreeNode root) {
                // Morris inorder traversal
                List<Integer> list = new ArrayList<>();
                TreeNode curr = root;
                while(curr != null) {
                    if(curr.left == null) {
                        list.add(curr.val);
                        curr = curr.right;
                    } else {
                        TreeNode temp = curr.left;
                        while(temp.right!=null && temp.right!=curr)
                            temp = temp.right;
                        if(temp.right == null) {
                            temp.right = curr;  // form thread
                            curr = curr.left;
                        } else {
                            // temp.right == curr
                            temp.right = null; // erase thread
                            list.add(curr.val); // left is processed
                            curr = curr.right;
                        }
                    }
                }
                return list;
            }
        }
  ```
  - For preorder, we need to set the thread to point to curr node's right so that once the left is traversed, we move to the right child of the curr tree

### [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/) ⭐️⭐️

- if both p and q are bigger recurse of root.right
- if both p and q are smaller recurse of root.left
- else return root

### [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/) ⭐️⭐️⭐️


### [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/description/)

- Very similar to level order traversal


### [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/)

- Do an in order traversal


### [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/description/) ⭐️

- Similar to Diameter of Binary Tree
- In diameter every node has 1 weight, here it will node's value
- Be careful to handle negative weights

### [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/) ⭐️⭐️

- Use pre order traversal


### [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/) ⭐️⭐️⭐️

- Somehow I wrote whatever code came to my mind and it worked!!


### [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/description/) ⭐️⭐️

- Store the horizontal position in the node's value as we dont care about node's value
- Then do a level order traversal and find the width at each level
- if node's horizontal position is `i` then right child will be `2i` and left will be `2i-1`

<br><br>

## BST operations implementation 
- Construct : easy
- Insert : easy
- [Delete : TODO](https://leetcode.com/problems/delete-node-in-a-bst/description/)
- Find : easy
- [Successor / predecessor : TODO](https://leetcode.com/problems/inorder-successor-in-bst/description/)








