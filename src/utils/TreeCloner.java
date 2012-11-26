package utils;

import grammar.DerivationTree;
import grammar.NonTerminal;
import jdsl.core.api.Position;
import jdsl.core.api.PositionIterator;

public class TreeCloner {
    
    public static DerivationTree cloneThisTree( DerivationTree t, Position posC, DerivationTree original, Position pos ){
        if (t == null) {
            t = new DerivationTree();
            posC = t.root();
        }

        posC.set( "Production", pos.get( "Production" ));
        posC.set( "Element", pos.get( "Element" ));
        
        if (pos.get( "Element" ) instanceof NonTerminal) {
            PositionIterator cE = original.children( pos );
            int i = 0;
            while (cE.hasNext()){
                t = cloneThisTree( t, t.insertChildAtRank( posC, i, null ), original, cE.nextPosition());
                i++;
            }
        }
        return t;
    }

}
