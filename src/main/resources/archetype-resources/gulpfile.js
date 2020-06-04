const { src, dest, series, parallel, watch } = require('gulp'),
	gulpSass = require('gulp-sass'),
	gulpConnect = require('gulp-connect');

const paths = {
    sass: 'src/main/sass/style.scss',
    dist: 'build/dist/',
    targetCss: 'target/html5/css/',
    targetWeb: 'target/html5/'
};


// Compile SASS files to build/dist/css/
function sass() {
    return src(paths.sass)
        .pipe(gulpSass({outputStyle: 'compressed'}))
        .pipe(dest(paths.dist + "css"))
        .pipe(gulpConnect.reload());
}

// Watch sass files and replace target css
function watchFiles(cb) {
    watch('src/main/sass/**/*.*', update);
    cb();
}

// Copy compiled css tbuilt theme to static web folder
function copyDist() {
    return src(paths.dist + "css/style.css")
        .pipe(dest(paths.targetCss));
}

// Serve sample document and reload for changes
function connect(cb) {
    gulpConnect.server({
        root: paths.targetWeb,
        livereload: true
    });
    cb();
}

const build = series(sass);
const update = series(build, copyDist);

exports.default = build;
exports.dev = series(update, parallel(connect, watchFiles));