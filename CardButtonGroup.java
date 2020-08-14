import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CardButtonGroup {
    private ArrayList cardButtons;
    private int sum = 0;

    public CardButtonGroup() {
        cardButtons = new ArrayList();
    }

    public void add(CardButton cb) {
        cardButtons.add(cb);
    }

    public ArrayList getCardButtons() {
        return cardButtons;
    }

    public CardButton get(int index) {
        return (CardButton) cardButtons.get(index);
    }

    public int count() {
        return cardButtons.size();
    }

    public int selectedCount() {
        int count = 0;
		
		Iterator it = cardButtons.iterator();
		
		while (it.hasNext()) {
			if (((CardButton) it.next()).isSelected())
                count++;
		}

        return count;
    }

    public void deselectAll() {
    	Iterator it = cardButtons.iterator();
    	
    	while (it.hasNext()) {
			((CardButton) it.next()).setSelected(false);
		}
    }

    public int getSum() {
        int sum = 0;

		Iterator it = cardButtons.iterator();
    	
    	while (it.hasNext()) {
    		CardButton cb = (CardButton) it.next();
			if (cb.isSelected())
                sum += cb.getCard().getNumericalValue();

		}

        return sum;
    }

    public ArrayList selectedButtons() {
        ArrayList selected = new ArrayList();

		Iterator it = cardButtons.iterator();

		while (it.hasNext()) {
    		CardButton cb = (CardButton) it.next();
			if (cb.isSelected())
                selected.add(cb);
		}

        return selected;
    }

    private ArrayList negs;
    
    public int[][][] getRegalTripleSums() {
        negs = new ArrayList();
        for (int i = 0; i < cardButtons.size(); i++)
            if (((CardButton) cardButtons.get(i)).getCard().getNumericalValue() < 0)
                negs.add(new Integer(i));

        if (negs.size() < 3) return null;

        int[][][] sums = new int[negs.size()][negs.size()][negs.size()];

        for (int i = 0; i < sums.length; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    sums[i][j][k] = 
                    		  ((CardButton) cardButtons.get(((Integer) negs.get(i)).intValue())).getCard().getNumericalValue()
                            + ((CardButton) cardButtons.get(((Integer) negs.get(j)).intValue())).getCard().getNumericalValue()
                            + ((CardButton) cardButtons.get(((Integer) negs.get(k)).intValue())).getCard().getNumericalValue();
                }
            }
        }
        return sums;
    }

    public void highlightHint() {
        for (int i = 0; i < cardButtons.size(); i++)
            for (int j = 0; j < i; j++) {
            	CardButton one = (CardButton) cardButtons.get(i);
            	CardButton two = (CardButton) cardButtons.get(j);
            	
                if (one.getCard().getNumericalValue() + two.getCard().getNumericalValue() == 11) {
                    one.setSelected(true);
                    two.setSelected(true);
                    return;
                }
            }

        int[][][] tSum = getRegalTripleSums();
        if (tSum != null) {
            for (int i = 0; i < tSum.length; i++) {
                for (int j = 0; j < i; j++)
                    for (int k = 0; k < j; k++)
                        if (tSum[i][j][k] == -6) {
                            ((CardButton) cardButtons.get(((Integer) negs.get(i)).intValue())).setSelected(true);
                            ((CardButton) cardButtons.get(((Integer) negs.get(j)).intValue())).setSelected(true);
                            ((CardButton) cardButtons.get(((Integer) negs.get(k)).intValue())).setSelected(true);
                            return;
                        }
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < cardButtons.size(); i++)
            for (int j = 0; j < i; j++) {
            	CardButton one = (CardButton) cardButtons.get(i);
            	CardButton two = (CardButton) cardButtons.get(j);
                if (one.getCard().getNumericalValue() + two.getCard().getNumericalValue() == 11) {
                    return false;
                }
            }

        int[][][] tSum = getRegalTripleSums();
        if (tSum != null) {
            for (int i = 0; i < tSum.length; i++) {
                for (int j = 0; j < i; j++)
                    for (int k = 0; k < j; k++)
                        if (tSum[i][j][k] == -6) {
                            return false;
                        }
            }
        }
        return true;
    }

    public boolean isEleven() {
        int count = selectedCount();
        int sum = getSum();

        if (count == 2 && sum == 11)
            return true;
        if (count == 3 && sum == -6)
            return true;
        return false;
    }
}
