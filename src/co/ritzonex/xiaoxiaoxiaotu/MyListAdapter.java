package co.ritzonex.xiaoxiaoxiaotu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import co.ritzonex.xiaoxiaoxiaotu.dummy.DummyContent;
import co.ritzonex.xiaoxiaoxiaotu.dummy.DummyContent.DummyItem;

import com.squareup.picasso.Picasso;

public class MyListAdapter extends BaseAdapter {
	private final Context context;

	// private final List<String> urls = new ArrayList<String>();

	public MyListAdapter(Context context) {
		this.context = context;
		// Collections.addAll(urls, Data.URLS);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.list_item,
					parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) view.findViewById(R.id.image);
			holder.text = (TextView) view.findViewById(android.R.id.text1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		// Get the image URL for the current position.
		DummyItem item = getItem(position);

		holder.text.setText(item.getTitle());

		// Trigger the download of the URL asynchronously into the image view.
		Picasso.with(context).load(item.getUrl())
				.placeholder(R.drawable.placeholder).error(R.drawable.error)
				// .resizeDimen(R.dimen.list_detail_image_size,
				// R.dimen.list_detail_image_size)
				// .centerInside()
				.into(holder.image);

		return view;
	}

	@Override
	public int getCount() {
		return DummyContent.ITEMS.size();
	}

	@Override
	public DummyItem getItem(int position) {
		return DummyContent.ITEMS.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		ImageView image;
		TextView text;
	}
}
