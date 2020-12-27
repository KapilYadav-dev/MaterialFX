package io.github.palexdev.materialfx.font;

import javafx.css.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Class used for MaterialFX font icon resources.
 */
public class MFXFontIcon extends Text {
    //================================================================================
    // Properties
    //================================================================================
    private static final StyleablePropertyFactory<MFXFontIcon> FACTORY = new StyleablePropertyFactory<>(Text.getClassCssMetaData());
    private final String STYLE_CLASS = "mfx-font-icon";

    //================================================================================
    // Constructors
    //================================================================================
    public MFXFontIcon(String description) {
        this(description, 10);
    }

    public MFXFontIcon(String description, double size) {
        this(description, size, Color.rgb(117, 117, 117));
    }

    public MFXFontIcon(String description, double size, Color color) {
        initialize();

        setDescription(description);
        setFont(FontHandler.getResources());
        setSize(size);
        setColor(color);

        setText(String.valueOf(FontHandler.getCode(description)));
    }

    //================================================================================
    // Methods
    //================================================================================
    private void initialize() {
        getStyleClass().setAll(STYLE_CLASS);

        sizeProperty().addListener((observable, oldValue, newValue) -> {
            Font font = getFont();
            setFont(Font.font(font.getFamily(), newValue.doubleValue()));
        });

        colorProperty().addListener((observable, oldValue, newValue) -> setFill(newValue));

        descriptionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                final char character = FontHandler.getCode(newValue);
                setText(String.valueOf(character));
            }
        });
    }

    /**
     * Specifies the icon code of the icon.
     */
    private final StyleableStringProperty description = new SimpleStyleableStringProperty(
            StyleableProperties.DESCRIPTION,
            this,
            "description"
    );

    /**
     * Specifies the size of the icon.
     */
    private final StyleableDoubleProperty size = new SimpleStyleableDoubleProperty(
            StyleableProperties.SIZE,
            this,
            "size",
            10.0
    );

    /**
     * Specifies the color of the icon.
     */
    private final StyleableObjectProperty<Color> color = new SimpleStyleableObjectProperty<>(
            StyleableProperties.COLOR,
            this,
            "color",
            Color.rgb(117, 117, 117)
    );

    public String getDescription() {
        return description.get();
    }

    public StyleableStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String code) {
        this.description.set(code);
    }

    public double getSize() {
        return size.get();
    }

    public StyleableDoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public Color getColor() {
        return color.get();
    }

    public StyleableObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    //================================================================================
    // CssMetaData
    //================================================================================
    public static class StyleableProperties {
        private static final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;

        private static final CssMetaData<MFXFontIcon, String> DESCRIPTION =
                FACTORY.createStringCssMetaData(
                        "-mfx-icon-code",
                        MFXFontIcon::descriptionProperty
                );

        private static final CssMetaData<MFXFontIcon, Number> SIZE =
                FACTORY.createSizeCssMetaData(
                        "-mfx-size",
                        MFXFontIcon::sizeProperty,
                        10
                );

        private static final CssMetaData<MFXFontIcon, Color> COLOR =
                FACTORY.createColorCssMetaData(
                        "-mfx-color",
                        MFXFontIcon::colorProperty,
                        Color.rgb(117, 117, 117)
                );

        static {
            cssMetaDataList = List.of(DESCRIPTION, SIZE, COLOR);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaDataList() {
        return StyleableProperties.cssMetaDataList;
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return MFXFontIcon.getClassCssMetaDataList();
    }
}