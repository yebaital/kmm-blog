package code.yousef.blog.theme

import com.varabyte.kobweb.silk.theme.colors.MutableSilkPalette
import com.varabyte.kobweb.silk.theme.colors.MutableSilkPalettes

val theme = MutableSilkPalettes(
    dark = MutableSilkPalette(
        background = DarkTheme.BODY.color,
        color = DarkTheme.TEXT.color,
        button = MutableSilkPalette.Button(
            default = DarkTheme.BUTTON.color,
            hover = DarkTheme.BUTTON_HOVER.color,
            focus = DarkTheme.BUTTON_FOCUS.color,
            pressed = DarkTheme.BUTTON_PRESSED.color
        ),
        link = MutableSilkPalette.Link(
            default = DarkTheme.LINK.color,
            visited = DarkTheme.VISITED_LINK.color,
        ),
        switch = MutableSilkPalette.Switch(
            backgroundOff = DarkTheme.SWITCH_OFF.color,
            backgroundOn = DarkTheme.SWITCH_ON.color,
            thumb = DarkTheme.SWITCH_KNOB.color
        ),
        tab = MutableSilkPalette.Tab(
            color = DarkTheme.TAB.color,
            background = DarkTheme.TAB_BACKGROUND.color,
            selectedColor = DarkTheme.TAB_SELECTED.color,
            hover = DarkTheme.TAB_HOVER.color,
            pressed = DarkTheme.TAB_PRESSED.color,
            disabled = DarkTheme.TAB_DISABLED.color,
            selectedBackground = DarkTheme.TAB_SELECTED_BACKGROUND.color,
            selectedBorder = DarkTheme.TAB_SELECTED_BORDER.color
        ),
        input = MutableSilkPalette.Input(
            filled = DarkTheme.INPUT_FILLED.color,
            hoveredBorder = DarkTheme.INPUT_HOVERED_BORDER.color,
            invalidBorder = DarkTheme.INPUT_INVALID_BORDER.color,
            filledHover = DarkTheme.INPUT_FILLED_HOVER.color,
            filledFocus = DarkTheme.INPUT_FILLED_FOCUS.color
        ),
        checkbox = MutableSilkPalette.Checkbox(
            background = DarkTheme.CHECKBOX_BACKGROUND.color,
            hover = DarkTheme.INPUT_HOVERED_BORDER.color,
            color = DarkTheme.INPUT_FILLED_FOCUS.color
        )
    ),
    light = MutableSilkPalette(
        background = LightTheme.BODY.color,
        color = LightTheme.TEXT.color,
        button = MutableSilkPalette.Button(
            default = LightTheme.BUTTON.color,
            hover = LightTheme.BUTTON_HOVER.color,
            focus = LightTheme.BUTTON_FOCUS.color,
            pressed = LightTheme.BUTTON_PRESSED.color
        ),
        link = MutableSilkPalette.Link(
            default = LightTheme.LINK.color,
            visited = LightTheme.VISITED_LINK.color,
        ),
        switch = MutableSilkPalette.Switch(
            backgroundOff = LightTheme.SWITCH_OFF.color,
            backgroundOn = LightTheme.SWITCH_ON.color,
            thumb = LightTheme.SWITCH_KNOB.color
        ),
        tab = MutableSilkPalette.Tab(
            color = LightTheme.TAB.color,
            background = LightTheme.TAB_BACKGROUND.color,
            selectedColor = LightTheme.TAB_SELECTED.color,
            hover = LightTheme.TAB_HOVER.color,
            pressed = LightTheme.TAB_PRESSED.color,
            disabled = LightTheme.TAB_DISABLED.color,
            selectedBackground = LightTheme.TAB_SELECTED_BACKGROUND.color,
            selectedBorder = LightTheme.TAB_SELECTED_BORDER.color
        ),
        input = MutableSilkPalette.Input(
            filled = LightTheme.INPUT_FILLED.color,
            hoveredBorder = LightTheme.INPUT_HOVERED_BORDER.color,
            invalidBorder = LightTheme.INPUT_INVALID_BORDER.color,
            filledHover = LightTheme.INPUT_FILLED_HOVER.color,
            filledFocus = LightTheme.INPUT_FILLED_FOCUS.color
        ),
        checkbox = MutableSilkPalette.Checkbox(
            background = LightTheme.CHECKBOX_BACKGROUND.color,
            hover = LightTheme.INPUT_HOVERED_BORDER.color,
            color = LightTheme.INPUT_FILLED_FOCUS.color
        )
    )
)