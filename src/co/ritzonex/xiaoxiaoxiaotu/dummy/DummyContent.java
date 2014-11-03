package co.ritzonex.xiaoxiaoxiaotu.dummy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	// public static Map<String, DummyItem> ITEM_MAP = new HashMap<String,
	// DummyItem>();

	// static {
	// // Add 3 sample items.
	// addItem(new DummyItem("1", "Item 1"));
	// addItem(new DummyItem("2", "Item 2"));
	// addItem(new DummyItem("3", "Item 3"));
	// }

	private static final String URL = "http://m.juyouqu.com/page/";
	private static int page = (int) (Math.random() * 150) + 1;

	public static void addItem(DummyItem item) {
		ITEMS.add(item);
		// ITEM_MAP.put(item.id, item);
	}

	// 连接网络更新数据
	public static void update() {
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(URL + page);
			page = (int) (Math.random() * 150) + 1;
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			readStream(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
		}
	}

	private static void readStream(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		in.close();
		handle(buffer.toString());
	}

	private static void handle(String html) {
		Pattern pattern = Pattern.compile("class=\"post-container\"(.*?)</a>");
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(html);

		while (matcher.find()) {
			DummyContent.addItem(new DummyItem(matcher.group(1)));
		}
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		private String title;
		private String url;
		private static Pattern patternTitle = Pattern.compile("alt='(.*?)'");
		private static Pattern patternUrl = Pattern.compile("src=\"(.*?)\"");

		public DummyItem(String input) {
			Matcher matcher = patternTitle.matcher(input);
			if (matcher.find())
				this.title = matcher.group(1);
			matcher = patternUrl.matcher(input);
			if (matcher.find(1)) {
				String temp = matcher.group(1);
				this.url = temp.substring(0, temp.length() - 5);
			}
		}

		public String getTitle() {
			return title;
		}

		public String getUrl() {
			return url;
		}

		@Override
		public String toString() {
			return "DummyItem [title=" + title + ", url=" + url + "]";
		}

	}
}
