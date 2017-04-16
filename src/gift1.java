/*
ID: enzocam1
LANG: JAVA
PROG: gift1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class gift1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
             PrintWriter pw = new PrintWriter("gift1.out")) {
            int numFriends = Integer.parseInt(br.readLine());
            Map<String, Integer> accounts = new HashMap<>();
            String[] orderedFriends = new String[numFriends];
            for (int i = 0; i < numFriends; i++) {
                orderedFriends[i] = br.readLine();
                accounts.put(orderedFriends[i], 0);
            }
            for (int i = 0; i < numFriends; i++) {
                String giver = br.readLine();
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int amount = Integer.parseInt(st.nextToken());
                int numRecipients = Integer.parseInt(st.nextToken());
                if (numRecipients == 0) continue;
                int amountPerRecipient = amount / numRecipients;
                for (int j = 0; j < numRecipients; j++) {
                    String recipient = br.readLine();
                    accounts.put(recipient, accounts.get(recipient) + amountPerRecipient);
                }
                accounts.put(giver, accounts.get(giver) - amountPerRecipient * numRecipients);
            }
            for (int i = 0; i < orderedFriends.length; i++) {
                String f = orderedFriends[i];
                pw.println(f + " " + accounts.get(f));
            }
            pw.flush();
        } catch (IOException e) {
        }
    }
}
