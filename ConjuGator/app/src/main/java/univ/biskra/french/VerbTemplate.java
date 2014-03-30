
package univ.biskra.french;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class VerbTemplate
{

    private void LoadVerbes(String Filename)
    {
        try
        {
            sxb = new SAXBuilder();
            document = sxb.build(new File(Filename));
            racine = document.getRootElement();
            ListeVerbes = racine.getChildren();
            verbes = ListeVerbes.iterator();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public VerbTemplate(String listverbs)
    {
        LoadVerbes(listverbs);
    }

    public String getTemplate(String inVerb)
    {
        String groupe = "";
        do
        {
            if(!verbes.hasNext())
                break;
            Element verbe = (Element)verbes.next();
            if(verbe == null)
                continue;
            String verbeinf = verbe.getChild("i").getText();
            if(!verbeinf.equals(inVerb))
                continue;
            groupe = verbe.getChild("t").getText();
            break;
        } while(true);
        return groupe;
    }

    public void finalize()
    {
        sxb = null;
        document = null;
        racine = null;
        ListeVerbes = null;
        verbes = null;
        System.gc();
    }

    private Document document;
    private Element racine;
    private List ListeVerbes;
    private SAXBuilder sxb;
    private Iterator verbes;
}
