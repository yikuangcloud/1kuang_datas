<template>
  <div class="screen">
    <!-- 标题 -->
    <div class="screen-title">
      <div class="title-text">南沙调度IOC驾驶舱</div>
    </div>
    <!-- 内容 -->
    <div class="screen-view">
      <!-- 左边 -->
      <div class="screen-view-left">
        <!-- 左上 -->
        <div class="screen-view-left-top">
          <!-- 左上左 -->
          <div class="screen-view-left-top-left view-border">
            <div class="view-title view-title-small">
              <div>药耗</div>
              <div class="split"></div>
              <div class="en">Drug Consumption</div>
            </div>
            <div class="view-body">
              <div class="view-content">
                <div class="view-data">
                  <div style="display: flex; align-items: center">
                    <div
                      class="view-data-icon"
                      :style="{
                        'background-image':
                          'url(' +
                          require('@/assets/images/药耗图标.png') +
                          ')',
                      }"
                    ></div>
                    <div class="view-data-title">当前药耗</div>
                  </div>
                  <div style="display: flex; align-items: center">
                    <div class="view-data-text">52648.0</div>
                    <div class="view-data-unit">元千吨</div>
                  </div>
                </div>
              </div>
              <div
                id="chart_drug"
                style="height: calc(100% - 5.2rem); width: 100%"
              ></div>
              <div
                class="view-choose"
                v-clickoutside="() => chooseBlur('drug')"
              >
                <input
                  class="input"
                  type="text"
                  :readonly="true"
                  :value="`2023年6月`"
                  @focus="() => chooseFocus('drug')"
                />
                <div class="arrow"></div>
                <div
                  class="view-choose-list"
                  v-if="chooseShowList.chooseShow_drug"
                >
                  <div class="view-choose-header">
                    <div class="left"></div>
                    <div>2023年</div>
                    <div class="right"></div>
                  </div>
                  <div class="view-choose-body">
                    <div
                      class="view-choose-item"
                      v-for="(item, index) in [
                        '1',
                        '2',
                        '3',
                        '4',
                        '5',
                        '6',
                        '7',
                        '8',
                        '9',
                        '10',
                        '11',
                        '12',
                      ]"
                      :key="index"
                    >
                      {{ item }}月
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-legend">
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #35d7ff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #008cff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
              </div>
            </div>
          </div>
          <!-- 左上中 -->
          <div class="screen-view-left-top-center view-border">
            <div class="view-title view-title-small">
              <div>能耗</div>
              <div class="split"></div>
              <div class="en">Energy Consumption</div>
            </div>
            <div class="view-body">
              <div class="view-content">
                <div class="view-data">
                  <div style="display: flex; align-items: center">
                    <div
                      class="view-data-icon"
                      :style="{
                        'background-image':
                          'url(' +
                          require('@/assets/images/能耗图标.png') +
                          ')',
                      }"
                    ></div>
                    <div class="view-data-title">当前能耗</div>
                  </div>
                  <div style="display: flex; align-items: center">
                    <div class="view-data-text">52648.0</div>
                    <div class="view-data-unit">度千吨</div>
                  </div>
                </div>
              </div>
              <div
                id="chart_energy"
                style="height: calc(100% - 5.2rem); width: 100%"
              ></div>
              <div
                class="view-choose"
                v-clickoutside="() => chooseBlur('energy')"
              >
                <input
                  class="input"
                  type="text"
                  :readonly="true"
                  :value="`2023年6月`"
                  @focus="() => chooseFocus('energy')"
                />
                <div class="arrow"></div>
                <div
                  class="view-choose-list"
                  v-if="chooseShowList.chooseShow_energy"
                >
                  <div class="view-choose-header">
                    <div class="left"></div>
                    <div>2023年</div>
                    <div class="right"></div>
                  </div>
                  <div class="view-choose-body">
                    <div
                      class="view-choose-item"
                      v-for="(item, index) in [
                        '1',
                        '2',
                        '3',
                        '4',
                        '5',
                        '6',
                        '7',
                        '8',
                        '9',
                        '10',
                        '11',
                        '12',
                      ]"
                      :key="index"
                    >
                      {{ item }}月
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-legend">
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #35d7ff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #008cff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
              </div>
            </div>
          </div>
          <!-- 左上右 -->
          <div class="screen-view-left-top-right view-border">
            <div class="view-title view-title-small">
              <div>供水量</div>
              <div class="split"></div>
              <div class="en">Water Supply</div>
            </div>
            <div class="view-body">
              <div class="view-content">
                <div class="view-data" style="width: 17rem">
                  <div style="display: flex; align-items: center">
                    <div
                      class="view-data-icon"
                      :style="{
                        'background-image':
                          'url(' +
                          require('@/assets/images/供水量图标.png') +
                          ')',
                      }"
                    ></div>
                    <div class="view-data-title">今日</div>
                  </div>
                  <div style="display: flex; align-items: center">
                    <div class="view-data-text">52648.0</div>
                    <div class="view-data-unit">万吨</div>
                  </div>
                </div>
                <div class="view-data" style="width: 17rem">
                  <div style="display: flex; align-items: center">
                    <div
                      class="view-data-icon"
                      :style="{
                        'background-image':
                          'url(' +
                          require('@/assets/images/供水量图标.png') +
                          ')',
                      }"
                    ></div>
                    <div class="view-data-title">本月</div>
                  </div>
                  <div style="display: flex; align-items: center">
                    <div class="view-data-text">52648.0</div>
                    <div class="view-data-unit">万吨</div>
                  </div>
                </div>
              </div>
              <div
                id="chart_water"
                style="height: calc(100% - 5.2rem); width: 100%"
              ></div>
              <div
                class="view-choose"
                v-clickoutside="() => chooseBlur('water')"
              >
                <input
                  class="input"
                  type="text"
                  :readonly="true"
                  :value="`2023年6月`"
                  @focus="() => chooseFocus('water')"
                />
                <div class="arrow"></div>
                <div
                  class="view-choose-list"
                  v-if="chooseShowList.chooseShow_water"
                >
                  <div class="view-choose-header">
                    <div class="left"></div>
                    <div>2023年</div>
                    <div class="right"></div>
                  </div>
                  <div class="view-choose-body">
                    <div
                      class="view-choose-item"
                      v-for="(item, index) in [
                        '1',
                        '2',
                        '3',
                        '4',
                        '5',
                        '6',
                        '7',
                        '8',
                        '9',
                        '10',
                        '11',
                        '12',
                      ]"
                      :key="index"
                    >
                      {{ item }}月
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-legend">
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #35d7ff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
                <div class="view-legend-item">
                  <div
                    class="legend-item-icon"
                    style="background-color: #008cff"
                  ></div>
                  <div class="legend-item-data">黄阁水厂</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 左中 -->
        <div class="screen-view-left-center">
          <!-- 左中左 -->
          <div class="screen-view-left-center-left view-border">
            <div class="view-title">
              <div>黄阁水厂</div>
              <div class="split"></div>
              <div class="en">Huangge Water Works</div>
            </div>
            <div
              class="view-body"
              style="display: flex; justify-content: space-between"
            >
              <div class="view-body-bisect">
                <div class="view-brief">
                  <div>取水</div>
                  <div class="view-brief-info">
                    <div>运行：<span>8</span></div>
                    <div>停止：<span>2</span></div>
                    <div>故障：<span>5</span></div>
                  </div>
                </div>
                <div class="view-content">
                  <div
                    class="view-content-item"
                    v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                    :key="index"
                  >
                    <div class="item-icon"></div>
                    <div>
                      <div style="display: flex; align-items: flex-end">
                        <div class="item-data">9985</div>
                        <div class="item-unit">m³/h</div>
                      </div>
                      <div class="item-text">取水氯化物</div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-body-bisect">
                <div class="view-brief">
                  <div>送水</div>
                  <div class="view-brief-info">
                    <div>运行：<span>8</span></div>
                    <div>停止：<span>2</span></div>
                    <div>故障：<span>5</span></div>
                  </div>
                </div>
                <div class="view-content">
                  <div
                    class="view-content-item"
                    v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                    :key="index"
                  >
                    <div class="item-icon"></div>
                    <div>
                      <div style="display: flex; align-items: flex-end">
                        <div class="item-data">9985</div>
                        <div class="item-unit">m³/h</div>
                      </div>
                      <div class="item-text">取水氯化物</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 左中右 -->
          <div class="screen-view-left-center-right view-border">
            <div class="view-title">
              <div>榄核水厂</div>
              <div class="split"></div>
              <div class="en">Lanhe Water Works</div>
            </div>
            <div
              class="view-body"
              style="display: flex; justify-content: space-between"
            >
              <div class="view-body-bisect">
                <div class="view-brief">
                  <div>取水</div>
                  <div class="view-brief-info">
                    <div>运行：<span>8</span></div>
                    <div>停止：<span>2</span></div>
                    <div>故障：<span>5</span></div>
                  </div>
                </div>
                <div class="view-content">
                  <div
                    class="view-content-item"
                    v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                    :key="index"
                  >
                    <div class="item-icon"></div>
                    <div>
                      <div style="display: flex; align-items: flex-end">
                        <div class="item-data">9985</div>
                        <div class="item-unit">m³/h</div>
                      </div>
                      <div class="item-text">取水氯化物</div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-body-bisect">
                <div class="view-brief">
                  <div>取水</div>
                  <div class="view-brief-info">
                    <div>运行：<span>8</span></div>
                    <div>停止：<span>2</span></div>
                    <div>故障：<span>5</span></div>
                  </div>
                </div>
                <div class="view-content">
                  <div
                    class="view-content-item"
                    v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                    :key="index"
                  >
                    <div class="item-icon"></div>
                    <div>
                      <div style="display: flex; align-items: flex-end">
                        <div class="item-data">9985</div>
                        <div class="item-unit">m³/h</div>
                      </div>
                      <div class="item-text">取水氯化物</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 左下 -->
        <div class="screen-view-left-bottom">
          <!-- 左下左 -->
          <div class="screen-view-left-bottom-left view-border">
            <div class="view-title">
              <div>取水泵站</div>
              <div class="split"></div>
              <div class="en">Water Intake Pumping Stations</div>
            </div>
            <div class="view-body">
              <div class="view-nav">
                <div class="view-nav-list">
                  <div class="view-nav-item active">取水泵1</div>
                  <div class="view-nav-item">取水泵2</div>
                </div>
                <div class="view-brief-info" style="background: #003579">
                  <div>运行：<span>8</span></div>
                  <div>停止：<span>2</span></div>
                  <div>故障：<span>5</span></div>
                </div>
              </div>
              <div class="view-content">
                <div
                  class="view-content-item"
                  v-for="(item, index) in [
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                  ]"
                  :key="index"
                >
                  <div class="item-icon"></div>
                  <div
                    style="
                      display: flex;
                      flex-direction: column;
                      justify-content: space-between;
                    "
                  >
                    <div style="display: flex; align-items: flex-end">
                      <div class="item-data">9985</div>
                      <div class="item-unit">m³/h</div>
                    </div>
                    <div>取水氯化物</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 左下右 -->
          <div class="screen-view-left-bottom-right view-border">
            <div class="view-title">
              <div>加压泵站</div>
              <div class="split"></div>
              <div class="en">Booster Pump Stations</div>
            </div>
            <div class="view-body">
              <div class="table">
                <div class="table-header column-width">
                  <div>名称</div>
                  <div>进口压力</div>
                  <div>出口压力</div>
                  <div>瞬时流量</div>
                  <div>累计流量</div>
                  <div>机组状态</div>
                </div>
                <div class="table-body">
                  <vue-seamless-scroll
                    :data="[1, 2, 3, 4, 5, 6]"
                    :class-option="{ ...scrollOption, limitMoveNum: 5 }"
                  >
                    <div
                      class="table-column column-width"
                      v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                      :key="index"
                    >
                      <div>凫洲大桥加压泵站</div>
                      <div class="data">120.50</div>
                      <div class="data">120.50</div>
                      <div class="data">120.50</div>
                      <div class="data">120.50</div>
                      <div>
                        <div class="view-bottom-item">
                          <div>1#</div>
                          <div class="item-img">
                            <div class="item-img-left"></div>
                            <div class="item-img-right">25</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </vue-seamless-scroll>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 中间 -->
      <div class="screen-view-center">
        <!-- 地图 -->
        <div class="screen-view-center-bottom" style="position: relative">
          <div id="map" style="height: 100%; width: 100%"></div>
          <div class="view-choose" v-clickoutside="() => chooseBlur('map')">
            <input
              class="input"
              type="text"
              :readonly="true"
              :value="`原水管线`"
              @focus="() => chooseFocus('map')"
            />
            <div class="arrow"></div>
            <div class="view-choose-list" v-if="chooseShowList.chooseShow_map">
              <div class="view-choose-item active">原水管线</div>
              <div class="view-choose-item">原水管线</div>
              <div class="view-choose-item">原水管线</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 右边 -->
      <div class="screen-view-right">
        <!-- 右上 -->
        <div class="screen-view-right-top">
          <!-- 右上左 -->
          <div class="screen-view-right-top-left view-border">
            <div class="view-title">
              <div>管网基本信息</div>
              <div class="split"></div>
              <div class="en">Basic Information of Pipeline Network</div>
            </div>
            <div class="view-body">
              <div class="view-data">
                <div style="display: flex; align-items: center">
                  <div class="view-data-icon"></div>
                  <div class="view-data-title">管线总长</div>
                </div>
                <div style="display: flex; align-items: center">
                  <div class="view-data-text">52648.0</div>
                  <div class="view-data-unit">米</div>
                </div>
              </div>
              <div class="view-content">
                <div class="view-content-item">
                  <div class="view-brief">管材和管龄</div>
                  <div
                    id="chart_pipe"
                    style="height: calc(100% - 3.2rem); width: 100%"
                  ></div>
                  <div class="view-nav">
                    <div class="view-nav-list">
                      <div class="view-nav-item active">管材</div>
                      <div class="view-nav-item">管龄</div>
                    </div>
                  </div>
                  <div
                    class="view-choose"
                    v-clickoutside="() => chooseBlur('pipe')"
                  >
                    <input
                      class="input"
                      type="text"
                      :readonly="true"
                      :value="`南沙水司`"
                      @focus="() => chooseFocus('pipe')"
                    />
                    <div class="arrow"></div>
                    <div
                      class="view-choose-list"
                      v-if="chooseShowList.chooseShow_pipe"
                    >
                      <div class="view-choose-item active">南沙水司</div>
                      <div class="view-choose-item">南沙水司</div>
                      <div class="view-choose-item">南沙水司</div>
                    </div>
                  </div>
                  <div class="view-legend">
                    <div
                      class="view-legend-item"
                      v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                      :key="index"
                    >
                      <div class="legend-item-icon"></div>
                      <div class="legend-item-text">普通铸铁管</div>
                      <div class="legend-item-data">20%</div>
                    </div>
                  </div>
                </div>
                <div class="view-content-item">
                  <div class="view-brief">二供泵房</div>
                  <div
                    id="chart_pump"
                    style="height: calc(100% - 3.2rem); width: 100%"
                  ></div>
                  <div
                    class="view-choose"
                    v-clickoutside="() => chooseBlur('pump')"
                  >
                    <input
                      class="input"
                      type="text"
                      :readonly="true"
                      :value="`南沙水司`"
                      @focus="() => chooseFocus('pump')"
                    />
                    <div class="arrow"></div>
                    <div
                      class="view-choose-list"
                      v-if="chooseShowList.chooseShow_pump"
                    >
                      <div class="view-choose-item active">南沙水司</div>
                      <div class="view-choose-item">南沙水司</div>
                      <div class="view-choose-item">南沙水司</div>
                    </div>
                  </div>
                  <div class="view-legend">
                    <div
                      class="view-legend-item"
                      v-for="(item, index) in [1, 2, 3]"
                      :key="index"
                      style="width: 100%"
                    >
                      <div class="legend-item-icon"></div>
                      <div class="legend-item-text">正常泵房</div>
                      <div class="legend-item-data">100台</div>
                    </div>
                  </div>
                </div>
                <div class="view-content-item">
                  <div class="view-brief">消防栓</div>
                  <div
                    id="chart_fire"
                    style="height: calc(100% - 3.2rem); width: 100%"
                  ></div>
                  <div class="view-legend">
                    <div
                      class="view-legend-item"
                      v-for="(item, index) in [1, 2, 3]"
                      :key="index"
                      style="width: 100%"
                    >
                      <div class="legend-item-icon"></div>
                      <div class="legend-item-text">正常消防栓</div>
                      <div class="legend-item-data">100台</div>
                    </div>
                  </div>
                </div>
                <div
                  class="view-content-item"
                  style="margin-bottom: 0; height: 22.9rem"
                >
                  <div class="view-brief">管网工单</div>
                  <div class="view-list">
                    <div
                      class="view-list-item"
                      v-for="(item, index) in [1, 2, 3, 4, 5]"
                      :key="index"
                    >
                      <div class="item-data">巡视 5/12</div>
                      <div class="item-progress">
                        <div class="item-progress-img">
                          <div style="width: 40%"></div>
                          <div style="width: 60%"></div>
                        </div>
                        <div class="item-progress-data">40%</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 右上右 -->
          <div class="screen-view-right-top-right view-border">
            <div class="view-title">
              <div>管线监测点</div>
              <div class="split"></div>
              <div class="en">Pipeline Monitoring Points</div>
            </div>
            <div
              class="view-body"
              style="display: flex; justify-content: space-between"
            >
              <div class="view-body-bisect">
                <div class="view-brief">原水管线关键监测点</div>
                <div class="view-content">
                  <div class="table">
                    <div class="table-header column-width">
                      <div>监测点名称</div>
                      <div>压力</div>
                      <div>上传时间</div>
                      <div>定位</div>
                    </div>
                    <div class="table-body">
                      <vue-seamless-scroll
                        :data="[
                          1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        ]"
                        :class-option="scrollOption"
                      >
                        <div
                          class="table-column column-width"
                          v-for="(item, index) in [
                            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                          ]"
                          :key="index"
                        >
                          <div>日产4S店</div>
                          <div class="data">120.50</div>
                          <div class="data">2023-06-20</div>
                          <div class="data">广东省深圳市罗湖区布吉街道</div>
                        </div>
                      </vue-seamless-scroll>
                    </div>
                  </div>
                </div>
              </div>
              <div class="view-body-bisect">
                <div class="view-brief">原水管线关键监测点</div>
                <div class="view-content">
                  <div class="table">
                    <div class="table-header column-width">
                      <div>监测点名称</div>
                      <div>压力</div>
                      <div>上传时间</div>
                      <div>定位</div>
                    </div>
                    <div class="table-body">
                      <vue-seamless-scroll
                        :data="[
                          1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        ]"
                        :class-option="scrollOption"
                      >
                        <div
                          class="table-column column-width"
                          v-for="(item, index) in [
                            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                          ]"
                          :key="index"
                        >
                          <div>日产4S店</div>
                          <div class="data">120.50</div>
                          <div class="data">2023-06-20</div>
                          <div class="data">广东省深圳市罗湖区布吉街道</div>
                        </div>
                      </vue-seamless-scroll>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 右下 -->
        <div class="screen-view-right-bottom">
          <!-- 右下左 -->
          <div class="screen-view-right-bottom-left view-border">
            <div class="view-title">
              <div>漏损主题</div>
              <div class="split"></div>
              <div class="en">Leakage</div>
            </div>
            <div class="view-body">
              <div class="view-nav">
                <div class="view-nav-list">
                  <div class="view-nav-item active">漏损率</div>
                  <div class="view-nav-item">产销差率</div>
                </div>
              </div>
              <div
                id="chart_leakage"
                style="height: calc(100% - 3.4rem); width: 100%"
              ></div>
              <div
                class="view-choose"
                v-clickoutside="() => chooseBlur('leakage')"
              >
                <input
                  class="input"
                  type="text"
                  :readonly="true"
                  :value="`2023年`"
                  @focus="() => chooseFocus('leakage')"
                />
                <div class="arrow"></div>
                <div
                  class="view-choose-list"
                  v-if="chooseShowList.chooseShow_leakage"
                >
                  <div class="view-choose-item active">2023年</div>
                  <div class="view-choose-item">2023年</div>
                  <div class="view-choose-item">2023年</div>
                </div>
              </div>
              <div class="view-legend">
                <div class="view-legend-item">
                  <div class="legend-item-icon-month"></div>
                  <div class="legend-item-text">月度值</div>
                </div>
                <div class="view-legend-item">
                  <div class="legend-item-icon-month-year"></div>
                  <div class="legend-item-text">月度值(上年)</div>
                </div>
                <div class="view-legend-item">
                  <div class="legend-item-icon-total"></div>
                  <div class="legend-item-text">累计值</div>
                </div>
                <div class="view-legend-item">
                  <div class="legend-item-icon-total-year"></div>
                  <div class="legend-item-text">累计值(上年)</div>
                </div>
              </div>
            </div>
          </div>
          <!-- 右下右 -->
          <div class="screen-view-right-bottom-right view-border">
            <div class="view-title">
              <div>客服主题</div>
              <div class="split"></div>
              <div class="en">Business Fees</div>
            </div>
            <div class="view-body">
              <div class="view-brief">营业收费</div>
              <div class="view-content">
                <div
                  class="view-content-item"
                  v-for="(item, index) in [1, 2, 3, 4, 5]"
                  :key="index"
                >
                  <div>报装工程处理量</div>
                  <div class="item-data">52648</div>
                </div>
              </div>
              <div class="view-brief">客服工单</div>
              <div class="view-content">
                <div
                  class="view-content-item"
                  v-for="(item, index) in [1, 2, 3, 4, 5, 6]"
                  :key="index"
                >
                  <div>报装工程处理量</div>
                  <div class="item-data">52648</div>
                </div>
              </div>
              <div class="view-brief">水表</div>
              <div class="view-content">
                <div
                  class="view-content-item"
                  v-for="(item, index) in [1, 2]"
                  :key="index"
                >
                  <div>报装工程处理量</div>
                  <div class="item-data">52648</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/assets/fonts/fonts.css";
import "cesium/Build/Cesium/Widgets/widgets.css";

import * as Cesium from "cesium";
import * as echarts from "echarts";
import vueSeamlessScroll from "vue-seamless-scroll";
import Clickoutside from "@/utils/clickoutside"; // 自定义指令，点击元素外的元素触发

import mapData from "./data";
import {
  getIndex,
  getHeights,
  handleSortArr,
  handleRectangleDegrees,
  handleDegreesArrayHeights,
} from "./map";

// 地图
let viewer = null;

export default {
  directives: { Clickoutside },
  components: {
    vueSeamlessScroll,
  },
  data() {
    return {
      // 字体
      htmlFontSize: 16,
      // 时间
      time: new Date(),
      timer: null,
      // 图表
      chartList: {},
      // 滚动
      scrollOption: {},
      // 下拉框
      chooseShowList: {
        chooseShow_drug: false,
        chooseShow_energy: false,
        chooseShow_water: false,
        chooseShow_pipe: false,
        chooseShow_pump: false,
        chooseShow_leakage: false,
        chooseShow_map: false,
      },
    };
  },
  computed: {
    timeTitle() {
      let week = "";
      switch (this.time.getDay()) {
        case 1:
          week = "星期一";
          break;
        case 2:
          week = "星期二";
          break;
        case 3:
          week = "星期三";
          break;
        case 4:
          week = "星期四";
          break;
        case 5:
          week = "星期五";
          break;
        case 6:
          week = "星期六";
          break;
        case 0:
          week = "星期日";
          break;
        default:
          break;
      }
      return this.formatDate(this.time, "yyyy-MM-dd hh:mm:ss") + " " + week;
    },
  },
  methods: {
    // 字体
    setHtmlFontSize() {
      let that = this;
      let docEl = document.documentElement;
      let recalc = () => {
        const clientWidth = docEl.clientWidth; // 得到设备宽度
        that.htmlFontSize = (clientWidth / 1152) * 2;
        docEl.style.fontSize = (clientWidth / 1152) * 2 + "px";
      };
      recalc();
      // window.addEventListener("resize", recalc, false);
      // document.addEventListener("DOMContentLoaded", recalc, false);
    },
    resetHtmlFontSize() {
      let that = this;
      let docEl = document.documentElement;
      that.htmlFontSize = 16;
      docEl.style.fontSize = 16 + "px";
    },
    rem(rate) {
      let that = this;
      return that.htmlFontSize * rate;
    },
    // 时间
    formatDate(date, fmt) {
      date = new Date(date);
      let ret;
      let opt = {
        "[yY]+": date.getFullYear().toString(), // 年
        "M+": (date.getMonth() + 1).toString(), // 月
        "[dD]+": date.getDate().toString(), // 日
        "[hH]+": date.getHours().toString(), // 时
        "m+": date.getMinutes().toString(), // 分
        "[sS]+": date.getSeconds().toString(), // 秒
      };
      for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
          fmt = fmt.replace(
            ret[1],
            ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, "0")
          );
        }
      }
      return fmt;
    },
    startTimeInterval() {
      this.timer = setInterval(() => {
        this.time = new Date();
      }, 1000);
    },
    clearTimeInterval() {
      clearInterval(this.timer);
    },
    // 地图
    initMap() {
      viewer = new Cesium.Viewer("map", {
        animation: false, // 禁用动画控件
        timeline: false, // 禁用时间线控件
        homeButton: false, // 禁用Home按钮
        sceneModePicker: false, // 禁用场景模式选择器
        baseLayerPicker: false, // 禁用图层选择器
        navigationHelpButton: false, // 禁用帮助按钮
        fullscreenButton: false, // 禁用全屏按钮
        geocoder: false, // 禁用搜索框
      });
      viewer.cesiumWidget.creditContainer.style.display = "none"; // 禁用logo
      let imageryLayers = viewer.imageryLayers;
      // 移出底图
      imageryLayers.removeAll();
      // 添加天地图
      let imagery = imageryLayers.addImageryProvider(
        new Cesium.WebMapTileServiceImageryProvider({
          url: "http://t0.tianditu.gov.cn/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=f69df935f2b4e5d06629d91c56be2809",
          format: "image/png",
          layer: "",
          style: "",
          tileMatrixSetID: "",
        })
      );
      imageryLayers.addImageryProvider(
        new Cesium.WebMapTileServiceImageryProvider({
          url: "http://t0.tianditu.gov.cn/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles&tk=f69df935f2b4e5d06629d91c56be2809",
          format: "image/png",
          layer: "",
          style: "",
          tileMatrixSetID: "",
        })
      );
      // 设置暗黑系底图
      imagery.hue = 3;
      imagery.contrast = -1.2;
    },
    setView(x, y) {
      if (x instanceof Array || y instanceof Array) {
        viewer.camera.setView({
          destination: Cesium.Rectangle.fromDegrees(
            ...handleRectangleDegrees(x, y)
          ),
          duration: 0,
          complete: () => {},
        });
      } else {
        viewer.camera.setView({
          destination: Cesium.Cartesian3.fromDegrees(x, y, 100000),
          duration: 0,
          complete: () => {},
        });
      }
    },
    addPointsPrimitives() {
      let data = mapData;
      // 点
      const length = data.junction_id.length;
      const pointsGeometry = [];
      for (let index = 0; index <= length - 1; index++) {
        pointsGeometry.push(
          new Cesium.GeometryInstance({
            geometry: new Cesium.CircleGeometry({
              center: Cesium.Cartesian3.fromDegrees(
                data.junction_y[index],
                data.junction_x[index]
              ),
              height: data.junction_elev[index],
              radius: 10,
            }),
            id: { id: data.junction_id[index], name: "point" },
            attributes: {
              color: new Cesium.ColorGeometryInstanceAttribute(
                20 / 255,
                204 / 255,
                137 / 255,
                1
              ),
            },
          })
        );
      }
      let primitive = new Cesium.Primitive({
        geometryInstances: pointsGeometry,
        appearance: new Cesium.PerInstanceColorAppearance({
          flat: true,
          translucent: false,
        }),
      });

      viewer.scene.primitives.add(primitive);
    },
    addLinesPrimitives() {
      let data = mapData;
      // 线
      const linesGeometry = [];
      const length = data.pipe_id.length;
      for (let index = 0; index <= length - 1; index++) {
        // heights不用使用handleSortArr方法排序，因为是通过pipe_startNode和pipe_endNode获取的，已经排好顺序
        let heights = getHeights(
          data.junction_elev[
            getIndex(data.pipe_startNode[index], data.junction_id)
          ],
          data.junction_elev[
            getIndex(data.pipe_endNode[index], data.junction_id)
          ],
          data.pipe_vertices[index]
        );
        linesGeometry.push(
          new Cesium.GeometryInstance({
            geometry: new Cesium.PolylineGeometry({
              positions: Cesium.Cartesian3.fromDegreesArrayHeights(
                handleDegreesArrayHeights(
                  handleSortArr(
                    data.pipe_vertices[index],
                    data.pipe_flow[index]
                  ),
                  heights
                )
              ),
              width: 2,
            }),
            id: { id: data.pipe_id[index], name: "line" },
          })
        );
      }
      let primitive = new Cesium.Primitive({
        geometryInstances: linesGeometry,
        appearance: new Cesium.PolylineMaterialAppearance({
          material: Cesium.Material.fromType("Color", {
            color: new Cesium.Color(0 / 255, 185 / 255, 255 / 255, 1),
          }),
        }),
      });

      viewer.scene.primitives.add(primitive);
    },
    // 图表
    // 药耗
    initChart_drug() {
      let x = [];
      let y = [];
      for (let i = 0; i < 12; i++) {
        x.push("06-01");
        y.push("60");
      }
      let that = this;
      let id = "chart_drug";
      let option = {
        legend: {
          show: false,
          data: [
            {
              name: "黄阁水厂",
              icon: "circle",
              itemStyle: { color: "#35D7FF" },
            },
            {
              name: "黄阁水长",
              icon: "circle",
              itemStyle: { color: "#008CFF" },
            },
          ],
          textStyle: {
            color: "#fff",
            fontSize: "0.9rem",
          },
          itemWidth: that.rem(0.6),
          selectedMode: false,
        },
        grid: {
          containLabel: true,
          top: that.rem(2),
          bottom: 0,
          left: 0,
          right: 0,
        },
        xAxis: {
          type: "category",
          data: x,
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: {
          type: "value",
          name: "单位：kWh",
          nameGap: that.rem(0.8),
          nameTextStyle: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          splitLine: {
            lineStyle: {
              type: "dashed",
              color: "#006BCF",
              width: that.rem(0.05),
            },
          },
        },
        series: [
          {
            name: "黄阁水厂",
            data: y,
            type: "bar",
            barWidth: that.rem(0.6),
            barGap: "65%",
            itemStyle: {
              color: "#35D7FF",
            },
          },
          {
            name: "黄阁水长",
            data: y,
            type: "bar",
            barWidth: that.rem(0.6),
            barGap: "65%",
            itemStyle: {
              color: "#008CFF",
            },
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 能耗
    initChart_energy() {
      let that = this;
      let id = "chart_energy";
      let option = {
        legend: {
          show: false,
          data: [
            {
              name: "黄阁水厂",
              icon: "circle",
              itemStyle: { color: "#35D7FF" },
            },
            {
              name: "黄阁水长",
              icon: "circle",
              itemStyle: { color: "#008CFF" },
            },
          ],
          textStyle: {
            color: "#fff",
            fontSize: "0.9rem",
          },
          itemWidth: that.rem(0.6),
          selectedMode: false,
        },
        grid: {
          containLabel: true,
          top: that.rem(2),
          bottom: 0,
          left: 0,
          right: 0,
        },
        xAxis: {
          type: "category",
          data: ["06-01"],
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: {
          type: "value",
          name: "单位：kWh",
          nameGap: that.rem(0.8),
          nameTextStyle: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          splitLine: {
            lineStyle: {
              type: "dashed",
              color: "#006BCF",
              width: that.rem(0.05),
            },
          },
        },
        series: [
          {
            name: "黄阁水厂",
            data: [75],
            type: "line",
            symbol: "none",
            lineStyle: {
              color: "#35D7FF",
              width: that.rem(0.15),
            },
          },
          {
            name: "黄阁水长",
            data: [60],
            type: "line",
            symbol: "none",
            lineStyle: {
              color: "#008CFF",
              width: that.rem(0.15),
            },
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 供水量
    initChart_water() {
      let x = [];
      let y = [];
      for (let i = 0; i < 31; i++) {
        x.push("06-01");
        y.push("60");
      }
      let that = this;
      let id = "chart_water";
      let option = {
        legend: {
          show: false,
          data: [
            {
              name: "黄阁水厂",
              icon: "circle",
              itemStyle: { color: "#35D7FF" },
            },
            {
              name: "黄阁水长",
              icon: "circle",
              itemStyle: { color: "#008CFF" },
            },
          ],
          textStyle: {
            color: "#fff",
            fontSize: "0.9rem",
          },
          itemWidth: that.rem(0.6),
          selectedMode: false,
        },
        grid: {
          containLabel: true,
          top: that.rem(2),
          bottom: 0,
          left: 0,
          right: 0,
        },
        xAxis: {
          type: "category",
          data: x,
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: {
          type: "value",
          name: "单位：万吨",
          nameGap: that.rem(0.8),
          nameTextStyle: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          splitLine: {
            lineStyle: {
              type: "dashed",
              color: "#006BCF",
              width: that.rem(0.05),
            },
          },
        },
        series: [
          {
            name: "黄阁水厂",
            data: y,
            type: "line",
            symbol: "none",
            lineStyle: {
              color: "#35D7FF",
              width: that.rem(0.15),
            },
          },
          {
            name: "黄阁水长",
            data: y,
            type: "line",
            symbol: "none",
            lineStyle: {
              color: "#008CFF",
              width: that.rem(0.15),
            },
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 管材和管龄
    initChart_pipe() {
      let that = this;
      let id = "chart_pipe";
      let option = {
        legend: {
          show: false,
          orient: "vertical",
          right: "right",
          top: "middle",
          itemGap: that.rem(1),
          itemWidth: that.rem(1),
          itemHeight: that.rem(1),
          icon: "circle",
          textStyle: {
            rich: {
              a: {
                color: "#fff",
                fontSize: "1.4rem",
              },
              b: {
                color: "#01a9ee",
                fontSize: "1.4rem",
              },
            },
          },
          formatter: function (name) {
            return `{a|${name}\t\t\t}{b|6520}`;
          },
          selectedMode: false,
        },
        series: [
          {
            name: "电耗",
            type: "pie",
            center: ["25%", "55%"],
            radius: ["50%", "65%"],
            label: {
              show: false,
            },
            labelLine: {
              show: false,
            },
            data: [
              {
                value: 6750,
                name: "普通铸铁管",
                itemStyle: {
                  color: "#008CFF",
                },
              },
              {
                value: 6520,
                name: "水泥管",
                itemStyle: {
                  color: "#E48B00",
                },
              },
              {
                value: 15000,
                name: "钢塑镀锌管",
                itemStyle: {
                  color: "#00AC75",
                },
              },
              {
                value: 1000,
                name: "球墨铸铁管",
                itemStyle: {
                  color: "#F74D4D",
                },
              },
              {
                value: 12500,
                name: "镀锌管",
                itemStyle: {
                  color: "#EBCB1C",
                },
              },
              {
                value: 12500,
                name: "PVC-U管",
                itemStyle: {
                  color: "#A132DB",
                },
              },
            ],
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 二供泵房
    initChart_pump() {
      let that = this;
      let id = "chart_pump";
      let option = {
        legend: {
          show: false,
          orient: "vertical",
          right: "right",
          top: "middle",
          itemGap: that.rem(1),
          itemWidth: that.rem(1),
          itemHeight: that.rem(1),
          icon: "circle",
          textStyle: {
            rich: {
              a: {
                color: "#fff",
                fontSize: "1.4rem",
              },
              b: {
                color: "#01a9ee",
                fontSize: "1.4rem",
              },
            },
          },
          formatter: function (name) {
            return `{a|${name}\t\t\t}{b|6520}`;
          },
          selectedMode: false,
        },
        series: [
          {
            name: "电耗",
            type: "pie",
            center: ["25%", "55%"],
            radius: ["50%", "65%"],
            label: {
              show: false,
            },
            labelLine: {
              show: false,
            },
            data: [
              {
                value: 6750,
                name: "正常泵房",
                itemStyle: {
                  color: "#008CFF",
                },
              },
              {
                value: 6520,
                name: "报警泵房",
                itemStyle: {
                  color: "#F74D4D",
                },
              },
              {
                value: 15000,
                name: "离线泵房",
                itemStyle: {
                  color: "#00479A",
                },
              },
            ],
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 消防栓
    initChart_fire() {
      let that = this;
      let id = "chart_fire";
      let option = {
        legend: {
          show: false,
          orient: "vertical",
          right: "right",
          top: "middle",
          itemGap: that.rem(1),
          itemWidth: that.rem(1),
          itemHeight: that.rem(1),
          icon: "circle",
          textStyle: {
            rich: {
              a: {
                color: "#fff",
                fontSize: "1.4rem",
              },
              b: {
                color: "#01a9ee",
                fontSize: "1.4rem",
              },
            },
          },
          formatter: function (name) {
            return `{a|${name}\t\t\t}{b|6520}`;
          },
          selectedMode: false,
        },
        series: [
          {
            name: "电耗",
            type: "pie",
            center: ["25%", "55%"],
            radius: ["50%", "65%"],
            label: {
              show: false,
            },
            labelLine: {
              show: false,
            },
            data: [
              {
                value: 6750,
                name: "正常消防栓",
                itemStyle: {
                  color: "#008CFF",
                },
              },
              {
                value: 6520,
                name: "报警消防栓",
                itemStyle: {
                  color: "#F74D4D",
                },
              },
              {
                value: 15000,
                name: "离线消防栓",
                itemStyle: {
                  color: "#00479A",
                },
              },
            ],
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 漏损主题
    initChart_leakage() {
      let x = [];
      let a = [];
      let b = [];
      let c = [];
      let d = [];
      for (let i = 0; i < 12; i++) {
        x.push("2023-01");
        a.push("60");
        b.push("50");
        c.push("40");
        d.push("30");
      }
      let that = this;
      let id = "chart_leakage";
      let option = {
        legend: {
          show: false,
          data: [
            {
              name: "黄阁水厂",
              icon: "circle",
              itemStyle: { color: "#35D7FF" },
            },
            {
              name: "黄阁水长",
              icon: "circle",
              itemStyle: { color: "#008CFF" },
            },
          ],
          textStyle: {
            color: "#fff",
            fontSize: "0.9rem",
          },
          itemWidth: that.rem(0.6),
          selectedMode: false,
        },
        grid: {
          containLabel: true,
          top: that.rem(2),
          bottom: 0,
          left: 0,
          right: 0,
        },
        xAxis: {
          type: "category",
          data: x,
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: {
          type: "value",
          name: "单位：kWh",
          nameGap: that.rem(0.8),
          nameTextStyle: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLabel: {
            fontSize: "0.8rem",
            color: "#fff",
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#006BCF",
              width: that.rem(0.1),
            },
          },
          splitLine: {
            lineStyle: {
              type: "dashed",
              color: "#006BCF",
              width: that.rem(0.05),
            },
          },
        },
        series: [
          {
            name: "月度值",
            data: a,
            type: "line",
            symbol: "circle",
            symbolSize: that.rem(0.7),
            itemStyle: {
              color: "#35D7FF",
            },
            lineStyle: {
              color: "#35D7FF",
              width: that.rem(0.15),
            },
          },
          {
            name: "月度值(上年)",
            data: b,
            type: "line",
            symbol: "circle",
            symbolSize: that.rem(0.55),
            itemStyle: {
              color: "transparent",
              borderColor: "#35D7FF",
              borderWidth: that.rem(0.15),
            },
            lineStyle: {
              color: "#35D7FF",
              width: that.rem(0.15),
              type: "dashed",
            },
          },
          {
            name: "累计值",
            data: c,
            type: "line",
            symbol: "circle",
            symbolSize: that.rem(0.7),
            itemStyle: {
              color: "#008CFF",
            },
            lineStyle: {
              color: "#008CFF",
              width: that.rem(0.15),
            },
          },
          {
            name: "累计值(上年)",
            data: d,
            type: "line",
            symbol: "circle",
            symbolSize: that.rem(0.55),
            itemStyle: {
              color: "transparent",
              borderColor: "#008CFF",
              borderWidth: that.rem(0.15),
            },
            lineStyle: {
              color: "#008CFF",
              width: that.rem(0.15),
              type: "dashed",
            },
          },
        ],
      };
      this.chartList[id] && this.chartList[id].clear();
      this.chartList[id] = echarts.init(document.getElementById(id));
      this.chartList[id] && this.chartList[id].resize();
      this.chartList[id].setOption(option);
    },
    // 滚动
    setScroll() {
      this.scrollOption = {
        step: this.rem(0.1), // 数值越大速度滚动越快
        limitMoveNum: 12, // 开始无缝滚动的数据量
        direction: 1, // 0向下 1向上 2向左 3向右
        hoverStop: true, // 是否开启鼠标悬停stop
        openWatch: true, // 开启数据实时监控刷新dom
      };
    },
    chooseFocus(id) {
      this.chooseShowList[`chooseShow_${id}`] = true;
    },
    chooseBlur(id) {
      this.chooseShowList[`chooseShow_${id}`] = false;
    },
  },
  created() {
    this.setHtmlFontSize();
    this.startTimeInterval();
    this.setScroll();
  },
  mounted() {
    // 地图
    this.initMap();
    this.addPointsPrimitives();
    this.addLinesPrimitives();
    // this.setView(113.6, 22.79); // 南沙区中心点
    this.setView(mapData.junction_y, mapData.junction_x);
    // 图表
    this.initChart_drug(); // 药耗
    this.initChart_energy(); // 能耗
    this.initChart_water(); // 供水量
    this.initChart_pipe(); // 管材和管龄
    this.initChart_pump(); // 二供泵房
    this.initChart_fire(); // 消防栓
    this.initChart_leakage(); // 漏损主题
  },
  destroyed() {
    this.resetHtmlFontSize();
    this.clearTimeInterval();
  },
};
</script>

<style lang="less" scoped>
.screen {
  background: linear-gradient(180deg, #002460 0%, #001545 100%);
  box-sizing: border-box;
  width: 576rem;
  height: 108rem;
  font-family: Source Han Sans CN;
}

.screen-title {
  box-sizing: border-box;
  height: 8rem;
  display: flex;
  justify-content: center;
  padding-top: 0.4rem;
  background-image: url("@/assets/images/大标题底部.png");

  background-size: 100% 100%;

  .title-text {
    font-size: 4rem;
    font-weight: 400;
    color: #fff;
    font-family: YouSheBiaoTiHei;
  }

  .title-time {
    font-size: 2.2rem;
    color: #00b9ff;
    flex: 1;
    height: 100%;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    border-bottom: 0.4rem solid #1970e4;
    position: relative;
  }

  .title-time::after {
    content: "";
    height: 0.4rem;
    width: 4rem;
    background-color: #0e3b98;
    position: absolute;
    bottom: 0rem;
    right: -5rem;
  }

  .title-weather {
    font-size: 2.2rem;
    color: #fff;
    flex: 1;
    height: 100%;
    display: flex;
    align-items: center;
    border-bottom: 0.4rem solid #1970e4;
    position: relative;
  }

  .title-weather::after {
    content: "";
    height: 0.4rem;
    width: 4rem;
    background-color: #0e3b98;
    position: absolute;
    bottom: 0rem;
    left: -5rem;
  }

  .title-deco {
    position: absolute;
    bottom: 0.8rem;
    display: flex;
  }

  .title-deco div {
    height: 0.4rem;
    width: 2.5rem;
    margin-left: 0.5rem;
  }

  .weather-city-change {
    font-size: 1.4rem;
    color: #00b9ff;
    border: 0.1rem solid #00b9ff;
    border-radius: 1rem;
    padding: 0 1rem;
    margin: 0 1rem;
  }

  .weather-temp {
    display: flex;
    align-items: center;
  }

  .weather-temp-content {
    font-size: 1.4rem;
    margin: 0 1rem;
  }
}

.screen-view {
  box-sizing: border-box;
  height: 100rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem 2.25rem 2.25rem 2.25rem;
}

.view-deco {
  position: relative;
}

.view-deco::after {
  content: "";
  height: 0.4rem;
  width: 2.5rem;
  background-color: #0e3b98;
  position: absolute;
  top: -1rem;
  right: 0rem;
}

.view-title {
  height: 4rem;
  font-size: 2rem;
  font-weight: 500;
  color: #ffffff;
  display: flex;
  align-items: center;
  padding-left: 3.5rem;
  background-image: url("@/assets/images/模块标题装饰.png");

  background-size: 100% 100%;

  .split {
    height: 2.4rem;
    width: 0.2rem;
    background: #bcdfff;
    margin: 0 1.05rem;
  }

  .en {
    font-size: 1.6rem;
    font-weight: 500;
    color: #abd2ff;
  }

  // &::before {
  //   content: "";
  //   position: absolute;
  //   width: 0.1rem;
  //   height: 1.2rem;
  //   left: 0;
  //   bottom: 0;
  //   background-color: #143580;
  // }

  .view-title-deco1 {
    position: absolute;
    width: 0;
    height: 0;
    border-left: 0.8rem solid transparent;
    border-right: 0.8rem solid transparent;
    border-top: 0.8rem solid #00b9ff;
    overflow: hidden;
    left: -0.8rem;
    bottom: 2.3rem;
  }

  .view-title-deco2 {
    position: absolute;
    width: 0;
    height: 0;
    border-left: 0.8rem solid transparent;
    border-right: 0.8rem solid transparent;
    border-bottom: 0.8rem solid #ff9259;
    overflow: hidden;
    left: -0.8rem;
    bottom: 1.2rem;
  }

  .view-title-deco3 {
    position: absolute;
    width: 0;
    height: 0;
    border-left: 0.8rem solid transparent;
    border-right: 0.8rem solid transparent;
    border-top: 0.8rem solid #00b9ff;
    overflow: hidden;
    right: -0.5rem;
    bottom: 0.5rem;
    transform: rotate(-45deg);
  }
}

.view-title-small {
  background-image: url("@/assets/images/小模块标题装饰.png");
}

.view-title-bg {
  color: #a4a6b6;
  font-size: 1.8rem;
  font-weight: 600;
  position: relative;
  display: inline-block;
  padding: 0.8rem 1.5rem 0.8rem 2rem;

  .view-title-bg-img {
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    border: 0.2rem solid #072e78;
    box-shadow: inset 0px 1rem 1rem -1rem #072e78,
      inset 0px -1rem 1rem -1rem #072e78, inset 1rem 0px 1rem -1rem #072e78,
      inset -1rem 0px 1rem -1rem #072e78;
    transform: skew(-30deg);
    cursor: pointer;
  }

  .active-txt {
    color: #ffffff;
  }

  .active-img {
    border: 0.2rem solid #5ebdfb;
    border-image: radial-gradient(circle, #02054c, #5ebdfb);
    border-image-slice: 1;
    box-shadow: inset 0px 1rem 1rem -1rem #5ebdfb,
      inset 0px -1rem 1rem -1rem #5ebdfb, inset 1rem 0px 1rem -1rem #5ebdfb,
      inset -1rem 0px 1rem -1rem #5ebdfb;
  }
}

.view-title-border {
  width: 12rem;
  height: 3rem;
  color: #fff;
  font-size: 1.8rem;
  font-weight: 600;
  background: linear-gradient(#00b9ff, #07105d) left top,
    linear-gradient(to right, #00b9ff, #07105d) left top,
    linear-gradient(#00b9ff, #07105d) right top,
    linear-gradient(to left, #00b9ff, #07105d) right top,
    linear-gradient(#07105d, #00b9ff) left bottom,
    linear-gradient(to left, #07105d, #00b9ff) left bottom,
    linear-gradient(#07105d, #00b9ff) right bottom,
    linear-gradient(to right, #07105d, #00b9ff) right bottom;

  background-size: 0.2rem 1.3rem, 1.3rem 0.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  background-color: #091462;
}

.view-title-border-column {
  width: 3rem;
  height: 12rem;
  color: #fff;
  font-size: 1.8rem;
  font-weight: 600;
  background: linear-gradient(#00b9ff, #07105d) left top,
    linear-gradient(to right, #00b9ff, #07105d) left top,
    linear-gradient(#00b9ff, #07105d) right top,
    linear-gradient(to left, #00b9ff, #07105d) right top,
    linear-gradient(#07105d, #00b9ff) left bottom,
    linear-gradient(to left, #07105d, #00b9ff) left bottom,
    linear-gradient(#07105d, #00b9ff) right bottom,
    linear-gradient(to right, #07105d, #00b9ff) right bottom;

  background-size: 0.2rem 1.3rem, 1.3rem 0.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  background-color: #091462;
}

.view-brief {
  background: #003579;
  height: 3.2rem;
  display: flex;
  align-items: center;
  font-size: 1.6rem;
  font-weight: 500;
  color: #ffffff;
  padding-left: 1.25rem;
  position: relative;

  &::before {
    content: "";
    position: absolute;
    width: 0.15rem;
    height: 1.6rem;
    left: 0;
    top: 50%;
    transform: translateY(-50%);
    background: #35d7ff;
  }
}

.view-brief-info {
  height: 2.4rem;
  width: 17.5rem;
  background: #002261;
  border-radius: 1.2rem;
  display: flex;
  font-size: 1.2rem;
  font-weight: 500;
  color: #abd2ff;
  align-items: center;
  justify-content: space-around;
  margin-left: 1.2rem;

  span {
    color: #fff;
  }
}

.view-body {
  box-sizing: border-box;
  height: calc(100% - 4rem);
  padding: 1rem;

  .view-body-bisect {
    width: 45.5rem;
    height: 100%;
  }
}

.screen-view-left {
  width: 189.5rem;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.screen-view-left-top {
  width: 100%;
  height: 28.95rem;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .view-content {
    display: flex;
    align-items: center;
    padding-bottom: 1rem;
  }

  .view-data {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 4rem;
    width: 25rem;
    border-radius: 0.4rem;
    padding: 0 1rem;
    background-image: url("@/assets/images/圆角矩形.png");
    background-size: 100% 100%;

    .view-data-icon {
      height: 2.4rem;
      width: 2.4rem;
      margin-right: 0.65rem;
      background-size: 100% 100%;
    }

    .view-data-title {
      font-size: 1.2rem;
      font-weight: 500;
      color: #abd2ff;
    }

    .view-data-text {
      font-size: 2.2rem;
      font-weight: 500;
      color: #ffffff;
      background: linear-gradient(
        180deg,
        #ffffff 0%,
        #35d7ff 50.48828125%,
        #008cff 100%
      );
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }

    .view-data-unit {
      font-size: 1rem;
      font-weight: 500;
      color: #abd2ff;
      margin-left: 0.55rem;
    }
  }

  .view-legend {
    position: absolute;
    top: 6.3rem;
    right: 11.95rem;
    display: flex;
    .view-legend-item {
      display: flex;
      align-items: center;
      margin-left: 0.95rem;
      .legend-item-icon {
        height: 0.6rem;
        width: 0.6rem;
        margin-right: 0.2rem;
      }
      .legend-item-data {
        font-size: 0.9rem;
        color: #fff;
      }
    }
  }

  .view-choose {
    font-size: 0.9rem;
    color: #fff;
    position: absolute;
    right: 1rem;
    top: 5.8rem;

    .input {
      position: absolute;
      box-sizing: border-box;
      right: 0;
      top: 0;
      padding: 0 0.8rem;
      width: 10rem;
      height: 2.4rem;
      background-color: #002e70;
      border: 0.05rem solid #006bcf;
      border-radius: 0.4rem;
      font-size: 0.9rem;
      color: #fff;
      cursor: pointer;
    }

    .input:focus {
      outline: none;
    }

    .arrow {
      background-image: url("@/assets/images/arrow_blue.png");
      background-size: 100% 100%;
      height: 1rem;
      width: 1rem;
      position: absolute;
      right: 0.8rem;
      top: 0.7rem;
      pointer-events: none;
    }

    .view-choose-list {
      position: absolute;
      text-align: center;
      right: 0;
      top: 2.4rem;
      border: 0.05rem solid #006bcf;
      box-sizing: border-box;
      background-color: #002e70;
      border-radius: 0.4rem;
      overflow: hidden;
      width: 20rem;
    }

    .view-choose-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 3rem;
      border-bottom: 0.1rem solid #002158;
      margin: 0 1rem;

      .left {
        background-image: url("@/assets/images/左双箭头.png");
        background-size: 100% 100%;
        height: 1rem;
        width: 1rem;
        cursor: pointer;
      }

      .right {
        background-image: url("@/assets/images/右双箭头.png");
        background-size: 100% 100%;
        height: 1rem;
        width: 1rem;
        cursor: pointer;
      }
    }

    .view-choose-body {
      display: flex;
      flex-wrap: wrap;
    }

    .view-choose-item {
      width: 25%;
      padding: 1rem 0;
      cursor: pointer;

      &:last-child {
        border-bottom: none;
      }

      &:hover {
        background-color: #003f89;
      }
    }
    .active {
      color: #35d7ff;
      font-weight: 700;
    }
  }
}

.screen-view-left-top-left {
  height: 100%;
  width: 62.25rem;
}

.screen-view-left-top-center {
  height: 100%;
  width: 62.25rem;
}

.screen-view-left-top-right {
  height: 100%;
  width: 62.25rem;
}

.screen-view-left-center {
  width: 100%;
  height: 32.3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .view-content {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding: 0.5rem 0;

    .view-content-item {
      height: 7rem;
      width: 22.45rem;
      font-size: 1.2rem;
      font-weight: 500;
      color: #abd2ff;
      border-radius: 0.4px;
      box-sizing: border-box;
      margin: 0.3rem 0;
      padding: 1.2rem 0 0 1.2rem;
      display: flex;
      background-image: url("@/assets/images/圆角矩形.png");

      background-size: 100% 100%;

      .item-icon {
        box-sizing: border-box;
        width: 1.95rem;
        height: 2.1rem;
        margin-right: 0.75rem;
      }

      .item-data {
        font-size: 2.2rem;
        line-height: 2.2rem;
        font-weight: 500;
        color: #ffffff;
        background: linear-gradient(
          180deg,
          #ffffff 0%,
          #35d7ff 50.48828125%,
          #008cff 100%
        );
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }

      .item-unit {
        font-size: 1rem;
        font-weight: 500;
        color: #abd2ff;
        margin-left: 0.55rem;
      }

      .item-text {
        margin-top: 0.5rem;
      }
    }
  }

  .view-bottom {
    display: flex;
    align-items: center;
    margin-top: 1.5rem;
    color: #fff;
    font-size: 1.4rem;
    padding-left: 1rem;

    .view-bottom-title {
      font-size: 1.8rem;
      font-weight: 600;
      margin-right: 1rem;
    }

    .view-bottom-item {
      background-color: #060f5c;
      border-radius: 0.5rem;
      padding: 1rem;
      box-sizing: border-box;
      display: flex;
      margin-right: 1.5rem;

      &:last-child {
        margin-right: 0;
      }

      .item-img {
        height: 2rem;
        width: 7rem;
        display: flex;
        margin-left: 1rem;
        border-radius: 0.5rem;
        overflow: hidden;
        color: #fff;

        .item-img-left {
          width: 50%;
          height: 100%;
          background-color: #344580;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .item-img-right {
          width: 50%;
          height: 100%;
          background-color: #14cc89;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
    }
  }
}

.screen-view-left-center-left {
  width: 94.1rem;
  height: 100%;
}

.screen-view-left-center-right {
  width: 94.1rem;
  height: 100%;
}

.screen-view-left-bottom {
  width: 100%;
  height: 32.3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.screen-view-left-bottom-left {
  width: 94.1rem;
  height: 100%;

  .view-nav {
    display: flex;
    align-items: center;

    .view-nav-list {
      display: flex;
      align-items: center;
      height: 2.4rem;
      width: 12rem;
      font-size: 1rem;
      font-weight: 400;
      color: #ffffff;
      border: 0.05rem solid #006bcf;
      border-radius: 0.2rem;
      overflow: hidden;
      background: rgba(0, 87, 169, 0);

      .view-nav-item {
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        cursor: pointer;
      }

      .active {
        background: #006bcf;
      }
    }
  }

  .view-content {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding: 0.5rem 0;

    .view-content-item {
      height: 4.6rem;
      width: 25%;
      font-size: 1.2rem;
      font-weight: 500;
      color: #abd2ff;
      border-radius: 0.4px;
      box-sizing: border-box;
      margin: 1.5rem 0;
      padding-left: 2rem;
      display: flex;

      .item-icon {
        height: 4.6rem;
        width: 4.8rem;
        box-sizing: border-box;
        margin-right: 0.95rem;
        background-image: url("@/assets/images/图标底.png");
        background-size: 100% 100%;
      }

      .item-data {
        font-size: 2.2rem;
        font-weight: 500;
        color: #ffffff;
        background: linear-gradient(
          180deg,
          #ffffff 0%,
          #35d7ff 50.48828125%,
          #008cff 100%
        );
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        line-height: 2.2rem;
      }

      .item-unit {
        font-size: 1rem;
        font-weight: 500;
        color: #abd2ff;
        margin-left: 0.55rem;
      }
    }
  }

  .view-bottom {
    display: flex;
    align-items: center;
    margin-top: 1.5rem;
    color: #fff;
    font-size: 1.4rem;
    padding-left: 1rem;

    .view-bottom-title {
      font-size: 1.8rem;
      font-weight: 600;
      margin-right: 1rem;
    }

    .view-bottom-item {
      background-color: #060f5c;
      border-radius: 0.5rem;
      padding: 1rem;
      box-sizing: border-box;
      display: flex;
      margin-right: 1.5rem;

      &:last-child {
        margin-right: 0;
      }

      .item-img {
        height: 2rem;
        width: 7rem;
        display: flex;
        margin-left: 1rem;
        border-radius: 0.5rem;
        overflow: hidden;
        color: #fff;

        .item-img-left {
          width: 50%;
          height: 100%;
          background-color: #344580;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .item-img-right {
          width: 50%;
          height: 100%;
          background-color: #14cc89;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
    }
  }
}

.screen-view-left-bottom-right {
  width: 94.1rem;
  height: 100%;

  .column-width {
    & > div:nth-child(1) {
      width: 12%;
    }
    & > div:nth-child(2) {
      width: 12%;
    }
    & > div:nth-child(3) {
      width: 12%;
    }
    & > div:nth-child(4) {
      width: 12%;
    }
    & > div:nth-child(5) {
      width: 12%;
    }
    & > div:nth-child(6) {
      width: 40%;
    }
  }

  .view-bottom-item {
    box-sizing: border-box;
    display: flex;
    font-size: 0.9rem;
    color: #fff;

    .item-img {
      height: 1.4rem;
      width: 4.4rem;
      display: flex;
      margin-left: 0.35rem;
      border-radius: 0.3rem;
      overflow: hidden;

      .item-img-left {
        width: 50%;
        height: 100%;
        background-color: #344580;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .item-img-right {
        width: 50%;
        height: 100%;
        background-color: #14cc89;
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
  }
}

.screen-view-center {
  box-sizing: border-box;
  width: 189.5rem;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.screen-view-center-top {
  box-sizing: border-box;
  height: 7.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.screen-view-center-bottom {
  box-sizing: border-box;
  height: 100%;
  display: flex;
  overflow: hidden;

  .view-choose {
    font-size: 1.4rem;
    font-weight: 500;
    color: #0057a9;
    position: absolute;
    right: 1rem;
    top: 1rem;

    .input {
      position: absolute;
      box-sizing: border-box;
      right: 0;
      top: 0;
      padding: 0 0.85rem;
      width: 20rem;
      height: 3rem;
      background-color: #fff;
      border: 0.05rem solid #0057a9;
      border-radius: 0.4rem;
      font-size: 1.4rem;
      font-weight: 500;
      color: #0057a9;
      cursor: pointer;
    }

    .input:focus {
      outline: none;
    }

    .arrow {
      background-image: url("@/assets/images/arrow_blue.png");
      background-size: 100% 100%;
      height: 1rem;
      width: 1rem;
      position: absolute;
      right: 0.85rem;
      top: 1rem;
      pointer-events: none;
    }

    .view-choose-list {
      width: 20rem;
      position: absolute;
      text-align: center;
      right: 0;
      top: 3rem;
      border: 0.05rem solid #0057a9;
      box-sizing: border-box;
      background-color: #fff;
      border-radius: 0.4rem;
      overflow: hidden;
    }

    .view-choose-item {
      padding: 0.5rem 0;

      &:last-child {
        border-bottom: none;
      }

      &:hover {
        background-color: #f5f7fa;
      }
    }

    .active {
      color: #409eff;
      font-weight: 700;
    }
  }
}

.screen-view-right {
  width: 189.5rem;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.screen-view-right-top {
  width: 100%;
  height: 55.75rem;
  display: flex;
  justify-content: space-between;
}

.screen-view-right-top-left {
  width: 94.1rem;
  height: 100%;

  .view-content {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding-top: 1rem;

    .view-content-item {
      width: 45.5rem;
      height: 21.9rem;
      margin-bottom: 1rem;
      position: relative;
    }
  }

  .view-data {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 4rem;
    width: 25rem;
    border-radius: 0.4rem;
    padding: 0 1rem;
    background-image: url("@/assets/images/圆角矩形.png");
    background-size: 100% 100%;

    .view-data-icon {
      height: 2.4rem;
      width: 2.4rem;
      margin-right: 0.65rem;
      background-image: url("@/assets/images/管线图标.png");
      background-size: 100% 100%;
    }

    .view-data-title {
      font-size: 1.2rem;
      font-weight: 500;
      color: #abd2ff;
    }

    .view-data-text {
      font-size: 2.2rem;
      font-weight: 500;
      color: #ffffff;
      background: linear-gradient(
        180deg,
        #ffffff 0%,
        #35d7ff 50.48828125%,
        #008cff 100%
      );
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }

    .view-data-unit {
      font-size: 1rem;
      font-weight: 500;
      color: #abd2ff;
      margin-left: 0.55rem;
    }
  }

  .view-list {
    height: calc(100% - 3.2rem);
    display: flex;
    flex-direction: column;
    justify-content: space-around;

    .view-list-item {
      .item-data {
        font-size: 1rem;
        font-weight: 500;
        color: #abd2ff;
      }
      .item-progress {
        display: flex;
        align-items: center;

        .item-progress-img {
          width: 41rem;
          height: 0.6rem;
          display: flex;
          overflow: hidden;

          div:nth-child(1) {
            background-color: #008cff;
            height: 100%;
          }

          div:nth-child(2) {
            background-color: #003479;
            height: 100%;
          }
        }
        .item-progress-data {
          font-size: 1.2rem;
          font-weight: 500;
          color: #008cff;
          margin-left: 1.05rem;
        }
      }
    }
  }

  .view-nav {
    display: flex;
    align-items: center;
    position: absolute;
    top: 4.2rem;
    left: 0;

    .view-nav-list {
      display: flex;
      align-items: center;
      height: 2.4rem;
      width: 12rem;
      font-size: 1rem;
      font-weight: 400;
      color: #ffffff;
      border: 0.05rem solid #006bcf;
      border-radius: 0.2rem;
      overflow: hidden;
      background: rgba(0, 87, 169, 0);

      .view-nav-item {
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        cursor: pointer;
      }

      .active {
        background: #006bcf;
      }
    }
  }

  .view-legend {
    position: absolute;
    top: 50%;
    right: 0;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    width: 22rem;
    z-index: 10;

    .view-legend-item {
      display: flex;
      align-items: center;
      width: 50%;
      margin-bottom: 1.2rem;

      .legend-item-icon {
        height: 0.6rem;
        width: 0.6rem;
        background-color: #008cff;
        border-radius: 50%;
        margin-right: 0.35rem;
      }

      .legend-item-text {
        font-size: 0.8rem;
        color: #abd2ff;
        margin-right: 0.9rem;
      }

      .legend-item-data {
        font-size: 0.9rem;
        color: #fff;
      }
    }
  }

  .view-choose {
    font-size: 0.9rem;
    color: #fff;
    position: absolute;
    right: 0rem;
    top: 4.2rem;
    z-index: 20;

    .input {
      position: absolute;
      box-sizing: border-box;
      right: 0;
      top: 0;
      padding: 0 0.8rem;
      width: 10rem;
      height: 2.4rem;
      background-color: #002e70;
      border: 0.05rem solid #006bcf;
      border-radius: 0.4rem;
      font-size: 0.9rem;
      color: #fff;
      cursor: pointer;
    }

    .input:focus {
      outline: none;
    }

    .arrow {
      background-image: url("@/assets/images/arrow_blue.png");
      background-size: 100% 100%;
      height: 1rem;
      width: 1rem;
      position: absolute;
      right: 0.8rem;
      top: 0.7rem;
      pointer-events: none;
    }

    .view-choose-list {
      width: 10rem;
      position: absolute;
      text-align: center;
      right: 0;
      top: 2.4rem;
      border: 0.05rem solid #006bcf;
      box-sizing: border-box;
      background-color: #002e70;
      border-radius: 0.4rem;
      overflow: hidden;
    }
    .view-choose-item {
      padding: 0.5rem 0;

      &:last-child {
        border-bottom: none;
      }

      &:hover {
        background-color: #003f89;
      }
    }
    .active {
      color: #35d7ff;
      font-weight: 700;
    }
  }
}

.screen-view-right-top-right {
  width: 94.1rem;
  height: 100%;

  .view-content {
    padding-top: 0.8rem;
    height: calc(100% - 4rem);
  }

  .column-width {
    & > div:nth-child(1) {
      width: 20%;
    }
    & > div:nth-child(2) {
      width: 20%;
    }
    & > div:nth-child(3) {
      width: 20%;
    }
    & > div:nth-child(4) {
      width: 40%;
    }
  }
}

.screen-view-right-bottom {
  width: 100%;
  height: 39rem;
  display: flex;
  justify-content: space-between;
}

.screen-view-right-bottom-left {
  width: 94.1rem;
  height: 100%;

  .view-nav {
    display: flex;
    align-items: center;
    padding-bottom: 1rem;

    .view-nav-list {
      display: flex;
      align-items: center;
      height: 2.4rem;
      width: 12rem;
      font-size: 1rem;
      font-weight: 400;
      color: #ffffff;
      border: 0.05rem solid #006bcf;
      border-radius: 0.2rem;
      overflow: hidden;
      background: rgba(0, 87, 169, 0);

      .view-nav-item {
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        cursor: pointer;
      }

      .active {
        background: #006bcf;
      }
    }
  }

  .view-choose {
    font-size: 0.9rem;
    color: #fff;
    position: absolute;
    right: 1rem;
    top: 5rem;

    .input {
      position: absolute;
      box-sizing: border-box;
      right: 0;
      top: 0;
      padding: 0 0.8rem;
      width: 10rem;
      height: 2.4rem;
      background-color: #002e70;
      border: 0.05rem solid #006bcf;
      border-radius: 0.4rem;
      font-size: 0.9rem;
      color: #fff;
      cursor: pointer;
    }

    .input:focus {
      outline: none;
    }

    .arrow {
      background-image: url("@/assets/images/arrow_blue.png");
      background-size: 100% 100%;
      height: 1rem;
      width: 1rem;
      position: absolute;
      right: 0.8rem;
      top: 0.7rem;
      pointer-events: none;
    }

    .view-choose-list {
      width: 10rem;
      position: absolute;
      text-align: center;
      right: 0;
      top: 2.4rem;
      border: 0.05rem solid #006bcf;
      box-sizing: border-box;
      background-color: #002e70;
      border-radius: 0.4rem;
      overflow: hidden;
    }
    .view-choose-item {
      padding: 0.5rem 0;

      &:last-child {
        border-bottom: none;
      }

      &:hover {
        background-color: #003f89;
      }
    }
    .active {
      color: #35d7ff;
      font-weight: 700;
    }
  }

  .view-legend {
    position: absolute;
    top: 5.5rem;
    right: 13rem;
    display: flex;
    align-items: center;
    flex-wrap: wrap;

    .view-legend-item {
      display: flex;
      align-items: center;

      .legend-item-icon-month {
        height: 0;
        width: 2.3rem;
        border-bottom: 0.15rem solid #35d7ff;
        margin-left: 0.9rem;
        position: relative;

        &::before {
          content: "";
          position: absolute;
          height: 0.6rem;
          width: 0.6rem;
          border-radius: 50%;
          background-color: #35d7ff;
          left: 50%;
          top: 0.075rem;
          transform: translateX(-50%) translateY(-50%);
        }
      }

      .legend-item-icon-month-year {
        height: 0;
        width: 2.3rem;
        border-bottom: 0.15rem dashed #35d7ff;
        margin-left: 0.9rem;
        position: relative;

        &::before {
          content: "";
          position: absolute;
          height: 0.6rem;
          width: 0.6rem;
          border-radius: 50%;
          border: 0.15rem solid #35d7ff;
          left: 50%;
          top: 0.075rem;
          transform: translateX(-50%) translateY(-50%);
          box-sizing: border-box;
        }
      }

      .legend-item-icon-total {
        height: 0;
        width: 2.3rem;
        border-bottom: 0.15rem solid #008cff;
        margin-left: 0.9rem;
        position: relative;

        &::before {
          content: "";
          position: absolute;
          height: 0.6rem;
          width: 0.6rem;
          border-radius: 50%;
          background-color: #008cff;
          left: 50%;
          top: 0.075rem;
          transform: translateX(-50%) translateY(-50%);
        }
      }

      .legend-item-icon-total-year {
        height: 0;
        width: 2.3rem;
        border-bottom: 0.15rem dashed #008cff;
        margin-left: 0.9rem;
        position: relative;

        &::before {
          content: "";
          position: absolute;
          height: 0.6rem;
          width: 0.6rem;
          border-radius: 50%;
          border: 0.15rem solid #008cff;
          left: 50%;
          top: 0.075rem;
          transform: translateX(-50%) translateY(-50%);
          box-sizing: border-box;
        }
      }

      .legend-item-text {
        font-size: 0.8rem;
        color: #abd2ff;
        margin-left: 0.35rem;
      }
    }
  }
}

.screen-view-right-bottom-right {
  width: 94.1rem;
  height: 100%;

  .view-content {
    display: flex;
    flex-wrap: wrap;

    .view-content-item {
      width: 16.5%;
      height: 8.15rem;
      color: #fff;
      font-size: 1.2rem;
      font-weight: 500;
      color: #abd2ff;
      box-sizing: border-box;
      padding-left: 2.4rem;
      display: flex;
      justify-content: center;
      flex-direction: column;

      .item-data {
        font-size: 2.2rem;
        font-weight: 500;
        color: #ffffff;
        background: linear-gradient(
          180deg,
          #ffffff 0%,
          #35d7ff 50.48828125%,
          #008cff 100%
        );
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    }
  }
}

.view-border {
  background: rgba(0, 87, 169, 0.12);
  border-bottom-left-radius: 1.8rem;
  position: relative;

  &::before {
    content: "";
    position: absolute;
    left: 0;
    bottom: 0;
    background-image: url("@/assets/images/形状.png");
    height: 1.8rem;
    width: 1.8rem;
    background-size: 100% 100%;
  }

  &::after {
    content: "";
    box-sizing: border-box;
    position: absolute;
    right: 0;
    bottom: 0;
    height: 1.8rem;
    width: 1.8rem;
    border-right: 0.2rem solid #35d7ff;
    border-bottom: 0.2rem solid #35d7ff;
  }
}

.table {
  height: 100%;
  border-radius: 0.5rem;
  background-color: #002966;
  display: flex;
  flex-direction: column;

  .table-header {
    display: flex;
    align-items: center;
    font-size: 1.4rem;
    background-color: #003f89;
    border-bottom: 0.1rem solid #002158;
    color: #f2f3f8;
    font-weight: 500;
    font-size: 1.1rem;
    height: 2.7rem;

    & > div {
      display: flex;
      align-items: center;
      padding-left: 1.15rem;
    }
  }

  .table-body {
    flex: 1;
    overflow: hidden;
  }

  .table-column {
    display: flex;
    align-items: center;
    height: 2.7rem;
    font-size: 0.9rem;
    border-bottom: 0.1rem solid #002158;
    color: #ffffff;
    font-weight: 400;

    .data {
      color: #35d7ff;
    }

    & > div {
      display: flex;
      align-items: center;
      padding-left: 1.15rem;
    }
  }
}
</style>
