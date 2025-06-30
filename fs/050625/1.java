/*
### 🥁 Grand Parade Uniform Assignment 🥁

You are organizing a grand parade where marching bands will move in a straight line.
Each band must wear uniforms of **exactly one color**, chosen from **K available colors**.

To make the parade **visually appealing** and avoid monotony, you must follow this important guideline:

> **No more than two consecutive bands can wear uniforms of the same color.**

---

### **Input Format**

Two integers:

* `n` — the total number of bands
* `k` — the number of available uniform colors

---

### **Output Format**

Print a single integer: the total number of valid ways to assign uniform colors to all bands such that **no more than two consecutive bands wear the same color**.

---

### **Constraints**

* `1 ≤ n ≤ 10^5`
* `1 ≤ k ≤ 100`

---

### **Sample Input**

```
3 2
```

### **Sample Output**

```
6
```

---

### **Explanation**

For `n = 3` bands and `k = 2` colors (say Red and Blue), the valid sequences include:

* Red, Red, Blue
* Red, Blue, Red
* Red, Blue, Blue
* Blue, Blue, Red
* Blue, Red, Blue
* Blue, Red, Red

The total is `6` valid colorings.

---



 */