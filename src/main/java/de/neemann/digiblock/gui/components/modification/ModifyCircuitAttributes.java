/*
 * Copyright (c) 2017 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digiblock.gui.components.modification;

import de.neemann.digiblock.core.element.ElementAttributes;
import de.neemann.digiblock.draw.elements.Circuit;
import de.neemann.digiblock.lang.Lang;
import de.neemann.digiblock.undo.Modification;

/**
 * Sets all attributes of a circuit
 */
public class ModifyCircuitAttributes implements Modification<Circuit> {
    private final ElementAttributes attributes;

    /**
     * Creates a new instance
     *
     * @param attributes the attributes to set
     */
    public ModifyCircuitAttributes(ElementAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public void modify(Circuit circuit) {
        circuit.getAttributes().getValuesFrom(attributes);
    }

    @Override
    public String toString() {
        return Lang.get("mod_circuitAttrModified");
    }
}
