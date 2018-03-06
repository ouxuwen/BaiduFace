function BaiduFace() {

}
BaiduFace.prototype.open = function (arg0, success, error) {
    cordova.exec(success, error, 'BaiduFace', 'open', [arg0]);

}
BaiduFace.prototype.test = function (arg0, success, error) {
    cordova.exec(success, error, 'BaiduFace', 'test', [arg0]);
}
BaiduFace.prototype.puejie = function (arg0, success, error) {
    alert("pu");
}
module.exports = new BaiduFace();