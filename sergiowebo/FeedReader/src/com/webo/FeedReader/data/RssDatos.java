package com.webo.FeedReader.data;

public class RssDatos {

	/*ArticleItem[] items;
    List headlines;
    List links;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        headlines = new ArrayList();
        links = new ArrayList();
        
        try {
        	URL url = new URL("http://www.rtve.es/api/programas/22330/audios.rss");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openConnection().getInputStream(), "UTF_8");
            boolean insideItem = false;
            int eventType = xpp.getEventType();
            
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                        	items.
                            headlines.add(xpp.nextText()); //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem)
                            links.add(xpp.nextText()); //extract the link of article
                    }
                }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }
                eventType = xpp.next(); //move to next element
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
			e.printStackTrace();
		}
        
        // Para que se vea el fondo transparente
        this.getListView().setCacheColorHint(0);
        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headlines);
        
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_list, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_about:
    			Context context = this;
    			Intent intent = new Intent(context, AboutActivity.class);
    			startActivity(intent);
                return true;
        }
		return false;
    }*/
}
