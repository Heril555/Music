package com.heril.music.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.heril.music.HomeViewModel
import com.heril.music.R
import com.heril.music.Screen
import com.heril.music.screensInBottomBar
import com.heril.music.screensInDrawer
import com.heril.notes.ui.theme.BlueSky
import com.heril.notes.ui.theme.BorderColor
import com.heril.notes.ui.theme.NightSky
import kotlinx.coroutines.launch

val categories = listOf("EDM","Pop","K-Pop","Hip Hop","Phonk","Rap","Rock","Jazz","Classical","Country","Dubstep","Rhythm and Blues")
val grouped = listOf<String>("Favorites","Hits","New Releases","Trending","Popular Artists","Popular Albums")

@Composable
fun Navigation(
    navController: NavController,
    viewModel: HomeViewModel,
    pd: PaddingValues,
    isDarkMode: Boolean
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomBarScreen.Home.bRoute,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.BottomBarScreen.Home.bRoute){
            HomeView(isDarkMode)
        }
        composable(Screen.BottomBarScreen.Browse.bRoute){
            BrowseView(isDarkMode)
        }
        composable(Screen.BottomBarScreen.Library.bRoute){
            LibraryView(isDarkMode)
        }
        composable(Screen.DrawerScreen.Account.dRoute){
            AccountView(isDarkMode)
        }
        composable(Screen.DrawerScreen.Subscription.dRoute){
            SubscriptionView(isDarkMode)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView(
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val viewModel: HomeViewModel = viewModel()
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    //Allows us to find out on which Screen we currently are
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val title = remember {
        //Change that to currentScreen.title
        mutableStateOf(currentScreen.title)
    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    val bottomBar:@Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomBarScreen.Home){
            BottomNavigation(
                modifier = Modifier.wrapContentSize(),
                backgroundColor = colorResource(id = R.color.yellow)
            ) {
                screensInBottomBar.forEach {
                    item->
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.bTitle
                            )
                        },
                        label = {
                            Text(
                                text = item.bTitle,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                                )
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier)
    }) {
        Scaffold(
            bottomBar = bottomBar,
            topBar = {
                TopAppBarView(title.value,
                    onMoreClicked = {
                        scope.launch {
                            if(modalSheetState.isVisible){
                                modalSheetState.hide()
                            }else{
                                modalSheetState.show()
                            }
                        }
                    },
                    onBackNavClicked = {},
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = onToggleDarkMode,
                    onDrawerClicked = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }})
            },
            backgroundColor = if (isDarkMode) colorResource(id = R.color.black) else colorResource(id = R.color.white),
            scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                            item->
                        DrawerItem(
                            selected = currentRoute == item.dRoute,
                            item = item,
                            onDrawerItemClicked = {
                                scope.launch{
                                    scaffoldState.drawerState.close()
                                }
                                if(item.dRoute == "add_account"){
                                    // open add account screen
                                    dialogOpen.value = true
                                }else{
                                    controller.navigate(item.dRoute)
                                    title.value = item.dTitle
                                }
                            }
                        )
                    }
                }
            },
            drawerBackgroundColor = colorResource(id = R.color.yellow)
        ) {
            Navigation(controller, viewModel, it,isDarkMode)
            AccountDialog(dialogOpen,isDarkMode)
        }
    }
}

@Composable
fun TopAppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {},
    onMoreClicked: () -> Unit = {},
    onDrawerClicked: () -> Unit = {},
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()

    val navigationIcon: (@Composable () -> Unit)? = {
//        if (!title.contains("Home")) {
//            IconButton(onClick = onBackNavClicked) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Back"
//                )
//            }
//        } else {
            IconButton(onClick = {
                //Open drawer
                onDrawerClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Menu"
                )
            }
//        }
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .heightIn(max = 32.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.yellow),
        navigationIcon = navigationIcon,
        actions = {
            DarkModeToggle(
                isDarkMode = isDarkMode,
                onToggle = onToggleDarkMode,
                modifier = Modifier.padding(end = 2.dp)
            )
            IconButton(onClick = {
                onMoreClicked()
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
        }
    )
}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(colorResource(id = R.color.yellow))
    ) {
        Column(modifier = modifier.padding(top = 16.dp)) {
            Row(modifier = modifier.padding(top = 16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                    modifier = Modifier.padding(start = 16.dp, end = 10.dp)
                )
                Text("Settings", fontSize = 20.sp, color = Color.Black)
            }
            Row(modifier = modifier.padding(top = 18.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = "Share",
                    modifier = Modifier.padding(start = 16.dp,end = 10.dp)
                )
                Text("Share", fontSize = 20.sp, color = Color.Black)
            }
            Row(modifier = modifier.padding(top = 18.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_help),
                    contentDescription = "Help",
                    modifier = Modifier.padding(start = 16.dp,end = 10.dp)
                )
                Text("Help", fontSize = 20.sp, color = Color.Black)
            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    onDrawerItemClicked: () -> Unit,
    item: Screen.DrawerScreen
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(
                color = if (selected) colorResource(id = R.color.ochre_yellow) else colorResource(R.color.yellow)
            )
            .clickable {
                onDrawerItemClicked()
            }
            .drawWithCache {
                onDrawWithContent {

                    // draw behind the content the vertical line on the left
                    drawLine(
                        color = Color.DarkGray,
                        start = Offset.Zero,
                        end = Offset(0f, this.size.height),
                        strokeWidth = 1f
                    )

                    // draw the content
                    drawContent()
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.h5,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun DarkModeToggle(
    isDarkMode: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val switchWidth = 72.dp
    val switchHeight = 30.dp
    val handleSize = 25.dp
    val handlePadding = 4.dp

    val valueToOffset = if (isDarkMode) 1f else 0f
    val offset = remember { Animatable(valueToOffset) }
    val scope = rememberCoroutineScope()

    DisposableEffect(isDarkMode) {
        if (offset.targetValue != valueToOffset) {
            scope.launch {
                offset.animateTo(valueToOffset, animationSpec = tween(1000))
            }
        }
        onDispose { }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(switchHeight))
            .background(lerp(BlueSky, NightSky, offset.value))
            .border(3.dp, BorderColor, RoundedCornerShape(switchHeight))
            .toggleable(
                value = isDarkMode,
                onValueChange = onToggle,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        val backgroundPainter = painterResource(R.drawable.background)
        Canvas(modifier = Modifier.fillMaxSize()) {
            with(backgroundPainter) {
                val scale = size.width / intrinsicSize.width
                val scaledHeight = intrinsicSize.height * scale
                translate(top = (size.height - scaledHeight) * (1f - offset.value)) {
                    draw(Size(size.width, scaledHeight))
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.glow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(switchWidth)
                .graphicsLayer {
                    scaleX = 1.2f
                    scaleY = scaleX
                    translationX = lerp(
                        -size.width * 0.5f + handlePadding.toPx() + handleSize.toPx() * 0.5f,
                        switchWidth.toPx() - size.width * 0.5f - handlePadding.toPx() - handleSize.toPx() * 0.5f,
                        offset.value
                    )
                }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = handlePadding)
                .size(handleSize)
                .offset(x = (switchWidth - handleSize - handlePadding * 2f) * offset.value)
                .paint(painterResource(R.drawable.sun))
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(R.drawable.moon),
                contentDescription = null,
                modifier = Modifier
                    .size(handleSize)
                    .graphicsLayer {
                        translationX = size.width * (1f - offset.value)
                    }
            )
        }
    }
}