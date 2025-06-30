## some more common stream operations

foreach: 
iterates over elements
lambda has one argument return value is ignored
terminal operation
can also pass method references

map: applies a function to each element
filter: removes elements that dobnt satisfy a custom rule
sorted: sorts elements
limit: returns the first n elements 
distinct: removes duplicates
collect: gets elements out of the stream once were done(terminal operation)

collect(reductions):
reducer created with Collectors.reducing(0, (acc, cur)->acc+cur);
accepts initial accumulator and a function with 2 params: current value of acc and current stream value

count: counts all elements in a stream(terminal)
skip: gets rid of first n elements
findFirst: gets first stream element wrapped in optional (terminal)
toArray: return elements as an array(terminal)
flatMap: flatten the data structure (e.g.on stream consisting of lists)
peek: do something with each item (like forEach, but not terminal)






## A Java Stream is intrinsically linked to its source. It represents a sequence of elements from a data-providing source, such as a Collection, array, generator function, or I/O channel.
However, it is important to understand that a Stream does not store the elements itself; it is a pipeline for processing elements from the source. This linkage means: 
- Lazy Evaluation:
    Operations on a stream are not performed immediately when defined. Instead, they are executed only when a terminal operation (like collect(), forEach(), or count()) is invoked, and elements are consumed from the source only as needed.
- Single Use:
    A stream can be consumed only once. After a terminal operation is performed, the stream is considered consumed and cannot be reused. Attempting to operate on a consumed stream will result in an IllegalStateException.
- No Modification of Source:
    Stream operations are designed to be functional and do not modify the original source data. They produce a new stream or a result based on the transformations applied to the elements.



#### Here are the key Stream methods you should know:

### 1. Creation Methods
```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From array
String[] arr = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);

// Direct creation
Stream<String> stream = Stream.of("a", "b", "c");
```

### 2. Intermediate Operations
```java
// Filter
.filter(x -> condition)

// Map
.map(x -> transformation)
.mapToInt(x -> intValue)
.mapToDouble(x -> doubleValue)

// Sorted
.sorted()
.sorted(Comparator.reverseOrder())

// Distinct
.distinct()

// Peek (debugging)
.peek(x -> System.out.println(x))

// Skip/Limit
.skip(n)
.limit(n)
```

### 3. Terminal Operations
```java
// Collection operations
.collect(Collectors.toList())
.collect(Collectors.toSet())
.collect(Collectors.toMap(k -> key, v -> value))

// Reduction operations
.reduce(0, (a,b) -> a + b)
.count()
.sum() // for numeric streams
.average() // returns OptionalDouble
.min()
.max()

// Match operations
.anyMatch(predicate)
.allMatch(predicate)
.noneMatch(predicate)

// Find operations
.findFirst()
.findAny()

// ForEach
.forEach(action)
```

### 4. Specialized Streams
```java
// IntStream, DoubleStream, LongStream
IntStream.range(1, 5)
IntStream.rangeClosed(1, 5)

// Parallel streams
list.parallelStream()
stream.parallel()
```

### 5. Common Collectors
```java
// Collecting
.collect(Collectors.joining(", "))
.collect(Collectors.groupingBy(classifier))
.collect(Collectors.partitioningBy(predicate))
.collect(Collectors.summarizingInt(mapper))
```

Remember:
- Streams can only be used once
- Intermediate operations are lazy
- Terminal operations are eager
- Streams don't modify the original data source









## Collectors.groupingBy(classifier)
``>
- Groups elements using the classifier.
- Uses a `HashMap` and collects into `List<T>` by default.

📦 **Return Type:**  
`Map<K, List<T>>`

---

### ✅ 2. Two-Argument Version:
```java
Collectors.groupingBy(classifier, downstreamCollector)
```
- Groups elements using the classifier.
- Still uses a `HashMap`.
- Applies a custom downstream collector (like `counting()`, `toSet()`, etc.)

📦 **Return Type:**  
`Map<K, D>`, where `D` is the result of the downstream collector.

---

### ✅ 3. Three-Argument Version:
```java
Collectors.groupingBy(classifier, mapFactory, downstreamCollector)
```
- Full control:
  - How to group (classifier)
  - What kind of `Map` to use (`LinkedHashMap`, `TreeMap`, etc.)
  - How to collect the grouped values (`counting`, `mapping`, `toSet`, etc.)

📦 **Return Type:**  
`M<K, D>`, where `M` is the `Map` implementation (e.g., `TreeMap<K, Set<T>>`)

---

## 🔧 Example with 3 Args:
```java
Map<String, Long> freq = words.stream()
    .collect(Collectors.groupingBy(
        word -> word,
        LinkedHashMap::new,
        Collectors.counting()
    ));
```

## collectors
🔹 Common Downstream Collectors

You can combine groupingBy with:
Collector	What it Does
Collectors.counting()	Count elements in each group
Collectors.toSet()	Collect group values as Set<T>
Collectors.mapping()	Map and collect inner values
Collectors.summingInt()	Sum values in each group
Collectors.averagingInt()	Get average of values per group
Collectors.reducing()	Perform custom reduction in groups
Collectors.collectingAndThen()	Post-process after collection

🧠 When to Use

Use Collectors.groupingBy() when:

    You want to categorize stream elements.

    You want to count, sum, or transform items within each group.

    You need custom groupings and data shaping.

🛠 Tips

    Use .entrySet().stream() to process grouped maps.

    Use LinkedHashMap if insertion order matters.

    Use TreeMap for sorted keys.