package cput.ac.za.infoshare;

import android.test.AndroidTestCase;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import cput.ac.za.infoshare.domain.content.RawContent;
import cput.ac.za.infoshare.factories.content.RawContentFactory;
import cput.ac.za.infoshare.repository.content.Impl.RawContentRepositoryImpl;
import cput.ac.za.infoshare.repository.content.RawContentRepository;

/**
 * Created by THULEH on 2016/04/29.
 */
public class ContentTest extends AndroidTestCase {
    private static final String TAG="Raw Content TEST";
    private String id;

    public void testCreateReadUpdateDelete() throws Exception {
        RawContentRepository repo = new RawContentRepositoryImpl(this.getContext());
        // CREATE
        String cont = " Using any type of tobacco puts you on a collision course with cancer." +
                " Smoking has been linked to various types of cancer — including cancer of the lung, bladder," +
                " cervix and kidney. And chewing tobacco has been linked to cancer of the oral cavity and pancreas." +
                " Even if you don't use tobacco, exposure to secondhand smoke might increase your risk of lung cancer." +
                "Avoiding tobacco — or deciding to stop using it — is one of the most important health decisions you can make." +
                " It's also an important part of cancer prevention. If you need help quitting tobacco," +
                " ask your doctor about stop-smoking products and other strategies for quitting.";
        Map<String, String> stringStringMap = new HashMap<>();

        stringStringMap.put("creator", "thulebona Hadebe");
        stringStringMap.put("source", "mobile");
        stringStringMap.put("category", "uncategorized");
        stringStringMap.put("title", "HIV prevention");
        stringStringMap.put("content", cont);
        stringStringMap.put("status", "raw");
        stringStringMap.put("contentType", "Text");
        stringStringMap.put("org", "CPUT");
        RawContent createEntity = RawContentFactory.getRawContent(stringStringMap);
        RawContent insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
    }
}
