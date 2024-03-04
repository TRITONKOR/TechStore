package com.tritonkor.techstore.ui;

import java.io.IOException;

/**
 * The {@code Renderable} interface defines methods for rendering and displaying menus in the client interface.
 */
public interface Renderable {

    /**
     * Renders the client interface.
     *
     * @throws IOException If an I/O error occurs.
     */
    void render() throws IOException;

    /**
     * Displays the menu in the client interface.
     */
    void showMenu();
}
