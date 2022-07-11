package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();

			Thread th = new Thread(new GetThread());
			th.setDaemon(true);
			th.start();

            System.out.println("Enter your message: ");
			while (true) {
				String text = scanner.nextLine();
				if (text.isEmpty()) break;

				String to;
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				String [] words = text.split(" ");
				char[] symbol = words[0].toCharArray();
					if (symbol[0] == '@') {
						sb.append(words[0]);
						to = String.valueOf(sb.deleteCharAt(0));
						sb1.append(text);
						int index = text.indexOf(" ");
						text = String.valueOf(sb1.delete(0,index+1));
					} else {
						to = "everyone";
					}

				Message m = new Message(login, text, to);
				int res = m.send(Utils.getURL() + "/add");

				if (res != 200) { // 200 OK
					System.out.println("HTTP error ocurred: " + res);
					return;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
