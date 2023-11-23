# Compose StyleSheet

Compose StyleSheet is a flexible UI component framework for Jetpack Compose.

**This library is still under development. All APIs may change in the future.**

```kotlin
fun createStyleSheet(useDarkMode: Boolean): StyleSheet = StyleSheet {
    Colors.text += if (useDarkMode) Color.White else Color.Black
    Colors.subText += if (useDarkMode) Color.LightGray else Color.DarkGray
    Colors.background += if (useDarkMode) Color.Black else Color.White

    content {
        color += Colors.text
    }

    surface {
        background += Colors.background
    }

    text(TextTags.primary) {
        fontSize += 24.sp
    }

    text(TextTags.secondary) {
        fontSize += 14.sp
        color += Colors.subText
    }
}
```

## Setup

```kotlin
// build.gradle.kts
dependencies {
    implementation("com.moriatsushi.compose.stylesheet:compose-stylesheet:0.0.2")
}
```

## Usage

### Getting Started

To use Compose StyleSheet, you provide a StyleSheet in the root of your app.
You can use `themeStyleSheet` which supports dark and light mode.

```kotlin
@Composable
fun App() {
    val styleSheet = themeStyleSheet()
    ProvideStyleSheet(styleSheet) {
        /* ... */
    }
}
```

Then, you can use the components.

```kotlin
@Composable
fun Sample() {
    Surface {
        Text("Hello, Compose StyleSheet!")
    }
}
```

### StyleSheet

Colors, Fonts, Sizes, Component Styles, etc. are defined in the StyleSheet.

If you want to customize the `themeStyleSheet`, you can define a StyleSheet and merge it with the
`themeStyleSheet`.

```kotlin
val yourStyleSheet = StyleSheet {
    /* ... */
}

@Composable
fun App() {
    val styleSheet = StyleSheet.merge(themeStyleSheet(), yourStyleSheet)
    ProvideStyleSheet(styleSheet) {
        /* ... */
    }
}
```

You can also create a StyleSheet from scratch without using `themeStyleSheet`.

### Design Token (Colors, Fonts, Sizes, etc...)

Design tokens are named style values, such as colors, fonts, sizes, etc.

You can define design tokens as follows:

```kotlin
val primaryColor = Token("primaryColor", default = Color.Black)
val secondaryColor = Token("secondaryColor", default = Color.DarkGray)

val defaultFontFamily = Token("defaultFontFamily", default = FontFamily.SansSerif)
val defaultFontSize = Token("defaultFontSize", default = 16.sp)
val defaultMargin = Token("defaultMargin", default = 8.dp)
```

The values of the design tokens can be overridden using the style sheet.
For example, you can change the colors based on dark or light mode.

```kotlin
fun createStyleSheet(useDarkMode: Boolean): StyleSheet = StyleSheet {
    primaryColor += if (useDarkMode) Color.White else Color.Black
    secondaryColor += if (useDarkMode) Color.LightGray else Color.DarkGray
}
```

The values can be obtained as follows:

```kotlin
@Composable
fun Sample() {
    val primaryColorValue = primaryColor.value
    val secondaryColorValue = secondaryColor.value
}
```

### Content Style

You can use the StyleSheet to customize the content style of text, icon, etc.

```kotlin
val styleSheet = StyleSheet {
    content {
        // Set the text color to the primary color.
        // You can use design tokens here.
        color += primaryColor

        // Set the font size to 16sp.
        fontSize += 16.sp
    }
}
```

### Component Style

All components can be styled using the StyleSheet.

```kotlin
val styleSheet = StyleSheet {
    surface { // Custom the surface component
        // Set the background color of the surface component to #EEEEEE
        background += Color(0xFFEEEEEE)
    }
}
```

If there are child contents in the component, you can change the content style as follows:

```kotlin
val styleSheet = StyleSheet {
    surface {
        content {
            // Set the content color in the surface component to red
            color += Color.Red
        }
    }
}
```

### Tag

The tags can be used to define variations of a component.

First, you can create a tag with the target component:

```kotlin
val primaryText = Tag("primaryText", text) // A tag for the text component
val subText = Tag("subText", text)
```

Then, you need to set the style for each tag in the StyleSheet:

```kotlin
val styleSheet = StyleSheet {
    text(primaryText) {
        color += Color.Black
    }

    text(subText) {
        color += Color.Gray
    }
}
```

Finally, you can specify the tag when using the component:

```kotlin
@Composable
fun Sample() {
    Column {
        Text("Primary Text", tags = primaryText) // The text color is black
        Text("Sub Text", tags = subText) // The text color is gray
    }
}
```

When you specify multiple tags, the merged style is applied:

```kotlin
@Composable
fun Sample() {
    Column {
        Text("Primary large text", tags = primaryText + largeText)
    }
}
```
