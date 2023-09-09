import * as Cesium from "cesium";

// 获取list对应值key的下标
export function getIndex(key, list) {
  return list.indexOf(key);
}

// 根据经纬度计算两点之间的距离
export function getDistance(point1, point2) {
  let startPosition = new Cesium.Cartesian3.fromDegrees(point1[1], point1[0]);
  let endPosition = new Cesium.Cartesian3.fromDegrees(point2[1], point2[0]);
  let distance = Cesium.Cartesian3.distance(startPosition, endPosition);
  return distance;
}

// 根据起点startHeight高度和终点endHeight高度填充中间点高度（等差数列）
export function getHeights(startHeight = 0, endHeight = 0, arr) {
  let startNode = arr[0];
  let endNode = arr[arr.length - 1];
  let total_height = endHeight - startHeight;
  let total_distance = getDistance(startNode, endNode);
  let res = [];
  for (let i = 0; i <= arr.length - 1; i++) {
    let distance = getDistance(startNode, arr[i]);
    res.push(startHeight + total_height * (distance / total_distance));
  }
  return res;
}

// 根据某点到起点距离赋不同颜色值
export function getColors(arr, total_length, single) {
  let ratio = 1000;
  let startNode = arr[0];
  let endNode = arr[arr.length - 1];
  let total_distance = getDistance(startNode, endNode);
  let res = [];
  for (let index = 0; index <= arr.length - 1; index++) {
    let distance = getDistance(startNode, arr[index]);
    let length = total_length * (distance / total_distance);
    let color = new Cesium.Color(
      length / ratio,
      total_length / ratio,
      0.079,
      0
    );
    if (single) {
      color = new Cesium.Color(length / ratio, total_length / ratio, 1, 0);
    }
    res.push(color);
  }
  return res;
}

// 将arr和heights的数据格式转为cesium的数据格式
export function handleDegreesArrayHeights(arr, heights) {
  let res = [];
  arr.forEach((item, index) => {
    res = res.concat(item[1], item[0], heights[index]);
  });
  return res;
}

// 根据流量排序（一维数组排序）
export function handleSortList(arr, flow = 0, interval = 1) {
  let res = [];
  if (Number(flow) < 0) {
    for (let i = 0; i <= arr.length - 1; i += interval) {
      res.unshift(...arr.slice(i, i + interval));
    }
  } else {
    res = arr;
  }
  return res;
}

// 根据流量排序（二维数组排序）
export function handleSortArr(arr, flow = 0) {
  let res = Number(flow) < 0 ? arr.reverse() : arr;
  return res;
}

// 根据图层范围进行定位
export function handleRectangleDegrees(res_x, res_y) {
  var xmax = 0;
  var xmin = 180;
  var ymax = 0;
  var ymin = 90;
  res_x.forEach((x) => {
    xmax = Math.max(xmax, x);
    xmin = Math.min(xmin, x);
  });
  res_y.forEach((y) => {
    ymax = Math.max(ymax, y);
    ymin = Math.min(ymin, y);
  });
  var shift_x = (xmax - xmin) / 20;
  var shift_y = (ymax - ymin) / 20;
  return [xmin - shift_x, ymin - shift_y, xmax + shift_x, ymax + shift_y];
}
