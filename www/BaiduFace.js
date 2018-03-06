function BaiduFace() {

}
BaiduFace.prototype.open = function (arg0, success, error) {
    cordova.exec(success, error, 'BaiduFace', 'open', [arg0]);

}


module.exports = new BaiduFace();
