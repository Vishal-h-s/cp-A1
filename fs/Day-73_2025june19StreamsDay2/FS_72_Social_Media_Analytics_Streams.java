/*
 * A chat application stores each message with:
    • user ID (String)
    • message content (String)
    • timestamp (Instant)

Write a program that:
    1. Counts how many messages each user has sent.
    2. Determines each user’s last-seen time (the most recent timestamp).
    3. Sorts all users by:
         a. last-seen timestamp, newest first
         b. then by message count, highest first
    4. Prints the top three users by this combined ordering.
    
    
Expected Output:
----------------
Top 3 active users: [bob, alice, carol]
 */
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FS_72_Social_Media_Analytics_Streams{

    static class Message {
        String userId;
        String content;
        Instant timestamp;

        public Message(String userId, String content, Instant timestamp) {
            this.userId = userId;
            this.content = content;
            this.timestamp = timestamp;
        }

        public String getUserId() {
            return userId;
        }

        public Instant getTimestamp() {
            return timestamp;
        }
    }

    public static void main(String[] args) {

        List<Message> messages = List.of(
            new Message("alice", "Hi!", Instant.parse("2023-06-23T10:15:30Z")),
            new Message("bob", "Hello", Instant.parse("2023-06-23T11:00:00Z")),
            new Message("carol", "Yo", Instant.parse("2023-06-23T08:30:00Z")),
            new Message("alice", "How are you?", Instant.parse("2023-06-23T12:45:00Z")),
            new Message("bob", "Good, thanks!", Instant.parse("2023-06-23T13:10:00Z")),
            new Message("bob", "See you later", Instant.parse("2023-06-23T14:00:00Z")),
            new Message("carol", "Bye", Instant.parse("2023-06-23T09:45:00Z"))
        );

        // 1. Count messages per user
        Map<String, Long> messageCounts = messages.stream()
            .collect(Collectors.groupingBy(
                m -> m.userId,
                Collectors.counting()
            ));

        // 2. Last seen timestamp per user
        Map<String, Instant> lastSeen = messages.stream()
            .collect(Collectors.groupingBy(
                m -> m.userId,
                Collectors.collectingAndThen(
                    Collectors.maxBy((a, b) -> a.timestamp.compareTo(b.timestamp)),
                    opt -> opt.isPresent() ? opt.get().timestamp : Instant.EPOCH
                )
            ));

        // 3. Sort users by last seen (newest first), then by message count (highest first)
        List<String> sortedUsers = lastSeen.keySet().stream()
            .sorted((u1, u2) -> {
                int cmp = lastSeen.get(u2).compareTo(lastSeen.get(u1)); // newer first
                if (cmp == 0) {
                    cmp = Long.compare(messageCounts.getOrDefault(u2, 0L), messageCounts.getOrDefault(u1, 0L)); // more messages first
                }
                return cmp;
            })
            .limit(3)
            .collect(Collectors.toList());

        // 4. Output
        System.out.println("Top 3 active users: " + sortedUsers);
    }
}
