
package univ.biskra.french;

import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public class ConjugateResult
{

    public ConjugateResult(Element e, String verbe)
    {
        VerbEle = e;
        TraitBeforeConjugate(verbe);
    }

    private void TraitBeforeConjugate(String v)
    {
        Element term = VerbEle.getChild("infinitive");
        EndOfVerb = term.getChild("infinitive-present").getChild("p").getChild("i").getText();
        int radpos = v.lastIndexOf(EndOfVerb);
        radical = v.substring(0, radpos);
    }

    public String[] Conjug(String temps, String temps1)
    {
        String verbre[] = new String[6];
        List conjigaison = VerbEle.getChild(temps).getChild(temps1).getChildren();
        Iterator it = conjigaison.iterator();
        for(int i = 0; it.hasNext(); i++)
        {
            Element item = (Element)it.next();
            String ter = item.getChild("i").getText();
            String VerbFinal = (new StringBuilder()).append(radical).append(ter).toString();
            verbre[i] = VerbFinal;
        }

        return verbre;
    }

    public String Conjug(String temps, String temps1, String pron)
    {
        String VerbFinal = null;
        int stop = 0;
        for(int j = 0; j < pronoms.length; j++)
            if(pronoms[j] != null ? pronoms[j].equals(pron) : pron == null)
                stop = j;

        List conjigaison = VerbEle.getChild(temps).getChild(temps1).getChildren();
        Iterator it = conjigaison.iterator();
        int i = 0;
        do
        {
            if(!it.hasNext())
                break;
            Element item = (Element)it.next();
            if(stop == i)
            {
                String ter = item.getChild("i").getText();
                VerbFinal = (new StringBuilder()).append(radical).append(ter).toString();
                break;
            }
            i++;
        } while(true);
        return VerbFinal;
    }

    private String radical;
    private String EndOfVerb;
    private Element VerbEle;
    private String pronoms[] = {
        "je", "tu", "il/elle", "nous", "vous", "ils/elles"
    };
}
