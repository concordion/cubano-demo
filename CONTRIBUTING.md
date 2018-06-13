## Contributing (or Setting Up your Own Project to use CheckStyle/Findbugs)

All contributions are welcome, we do ask however that you follow our coding standards.  This is rather easy as we have checkstyle and code formatter configuration files ready to use.

For eclipse:

1. Install findbugs plugin

1. Install checkstyle plugin

1. Eclipse > Preferences > Checkstyle > New...

    Type: External configuration file
    Name: Cubano
    Location: <workspace>/cubano/config/checkstyle/checkstyle.xml

1. Eclipse > Preferences > Java > Code Style > Formatter > Import...

    File: <workspace>/cubano/config/formatter/formatter.xml

1. Eclipse > Preferences > Java > Editor > Save Actions

    Perform the selected actions on save: Checked
    Format source code: Format edited lines
    Organise imports: Checked

1. Eclipse > Project > Properties > Checkstyle

    Checkstyle active for this project: Checked
    Use the following check configuration for all files: Cubano - (Global)

1. To execute a Checkstyle/Findbugs build > `gradlew check -x test`
