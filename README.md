# 🎂 SkipList and the Bakery Base Minimization Problem

This project is divided into two parts:
1. **SkipList Implementation**
2. **Bakery Problem**: Solving the minimum base stacking problem using SkipList.

---

## 📌 Part 1: SkipList Implementation

Sure! Here's a **brief and precise overview of SkipList** you can include in your README or documentation:

---

## 🧭 What is a SkipList?

A **SkipList** is a probabilistic data structure that allows fast **search**, **insertion**, and **deletion**—with expected time complexity of **O(log n)**.

### 🔧 How it works:

* A SkipList consists of **multiple levels of sorted linked lists**.
* Can have duplicates( duplicates are inserted after existing nodes with the same value)
* The bottom level contains **all elements**.
* Each higher level acts as an **"express lane"**, skipping over elements to speed up searches.
* Nodes are promoted to higher levels based on a **randomized process** (coin-flip style).

### 📌 Key Properties:

* **Balanced on average** without the need for complex rotations (unlike trees).
* **Simple to implement** compared to balanced trees.
* **Space-efficient**, as not all nodes appear in all levels.


### 🔍 Method Explanations

| Method | Description |
|--------|-------------|
| `insert(int num)` | Inserts a number with randomized height. Updates list levels accordingly. |
| `delete(int num)` | Deletes the node with value `num` by updating all levels that point to it. |
| `search(int num)` | Returns `true` if the number exists in the list; uses top-down traversal. |
| `upperBound(int num)` | Returns the smallest number strictly greater than `num`. Used for finding next valid stack position. |
| `print()` | Displays the entire skip list level-wise (debugging utility). |

### ⏱️ Time Complexity

| Operation | Avg Time | Worst Case |
|-----------|----------|------------|
| Search    | O(log n) | O(n)       |
| Insert    | O(log n) | O(n)       |
| Delete    | O(log n) | O(n)       |

SkipLists offer similar performance to balanced BSTs (like AVL/Red-Black) but are easier to implement.

---

## 🍰 Part 2: The Bakery Problem (Minimizing Cake Bases)

### Problem Description

You are given a sequence of cake sizes. To minimize the number of *cake bases* (bottom layers), you can **stack a cake on another only if it's smaller in size**.

Think of it like **making a tower of cakes** — if a smaller cake comes, it can be stacked on a larger one. Otherwise, we must start a new base.

### 🔧 Algorithm

- Use a **SkipList** to maintain all current base cakes (top of each tower).
- For each new cake:
  - **Find the smallest cake larger than it** → use `upperBound`.
  - If found, **replace that cake** (i.e., stack on it).
  - Else, **insert it as a new base**.
- The final count of inserted new bases = **minimum number of towers required**.

### 📈 Why SkipList?

- Efficient `upperBound`, `insert`, and `delete` in average **O(log n)**.
- Handles dynamic data better than arrays or sorted lists.
- Outperforms TreeSet for custom search + delete patterns with better control.

### 🧪 Example

```java
Input: [10, 4, 5, 9, 4, 10, 2, 7, 4, 6]
```
## Visual stacking:
```
Stack 1: 2 → 4 → 5 → 10
Stack 2: 4 → 6 → 9
Stack 3: 4 → 7
Stack 4: 10
```
## Output:
Minimum bases needed = 4

