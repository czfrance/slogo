open module slogo_app {
    // list all imported class packages since they are dependencies
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires javafx.web;
  requires java.desktop;
  requires java.net.http;

  // allow other classes to access listed packages in your project
    exports slogo;
  exports slogo.Console;
  exports slogo.Model;
  exports slogo.CompilerPackage;
}
